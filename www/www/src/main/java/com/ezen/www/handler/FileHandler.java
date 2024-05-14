package com.ezen.www.handler;

import com.ezen.www.domain.FileVO;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.tika.Tika;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@Slf4j
public class FileHandler {

    private String UP_DIR="C:\\leechanggyu\\_myProject\\_java\\_fileUpload\\";

    public List<FileVO> uploadFiles(MultipartFile[] files){
        List<FileVO> flist = new ArrayList<>();
        LocalDate date = LocalDate.now();
        String today = date.toString();
        //2024-05-14
        today = today.replace("-", File.separator);
        
        File folders = new File(UP_DIR, today);
        
        if (!folders.exists()){
            folders.mkdirs(); //여러개 폴더 생성
        }
        //---------폴더 생성 완료
        //fileVO 생성
        for(MultipartFile file : files){
            FileVO fvo = new FileVO();
            fvo.setSave_dir(today);
            fvo.setFile_size(file.getSize());

            String originalFileName = file.getOriginalFilename();
            String onlyFileName = originalFileName.substring(
                    originalFileName.lastIndexOf(File.separator)+1

            );
            fvo.setFile_name(onlyFileName);

            UUID uuid = UUID.randomUUID();
            String uuidStr = uuid.toString();
            fvo.setUuid(uuidStr);

            String fullFileName = uuid+"_"+onlyFileName;
            File storeFile = new File(folders, fullFileName);

            try {
                file.transferTo(storeFile);
                if(isImageFile(storeFile)){
                    fvo.setFile_type(1);
                    File thumbNail = new File(folders, uuidStr+"_th_"+onlyFileName);
                    Thumbnails.of(storeFile).size(100,100).toFile(thumbNail);
                }
            } catch (IOException e) {
                log.info("파일 설정 오류");
                e.printStackTrace();
            }
            //for문 안
            flist.add(fvo);
        }
        return flist;
    }

    private boolean isImageFile(File file) throws IOException {
        String mimeType = new Tika().detect(file);
        return mimeType.startsWith("image")? true : false;
    }



}
