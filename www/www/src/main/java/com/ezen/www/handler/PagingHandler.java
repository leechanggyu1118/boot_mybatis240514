package com.ezen.www.handler;

import com.ezen.www.domain.CommentVO;
import com.ezen.www.domain.PagingVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Getter
@Setter
@ToString
public class PagingHandler {

    private int startPage;
    private int endPage;
    private boolean prev;
    private boolean next;

    private int totalCount;
    private PagingVO pgvo;

    /*Comment List 추가*/
    private List<CommentVO> cmtList;

    private int realEndPage;

    public PagingHandler(PagingVO pgvo,  int totalCount){
        this.pgvo = pgvo;
        this.totalCount = totalCount;

        this.endPage = (int)Math.ceil(pgvo.getPageNo() / (double)10) * 10;
        this.startPage = endPage - 9;

        this.realEndPage = (int)Math.ceil(totalCount / (double)pgvo.getQty());

        if(endPage > realEndPage){
            this.endPage = realEndPage;
        }

        this.prev = this.startPage > 1;
        this.next = this.endPage < realEndPage;

    }

    /*Comment용 생성자 추가*/
    public PagingHandler(PagingVO pgvo, int totalCount, List<CommentVO> cmtList){
        this(pgvo, totalCount);
        this.cmtList = cmtList;
    }




}
