package com.ezen.www.repository;

import com.ezen.www.domain.BoardVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {

    int insert(BoardVO bvo);

    List<BoardVO>  list();

    BoardVO getDetail(long bno);

    int modify(BoardVO bvo);

    void remove(long bno);
}
