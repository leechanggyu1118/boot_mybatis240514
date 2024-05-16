package com.ezen.www.repository;

import com.ezen.www.domain.AutoVO;
import com.ezen.www.domain.MemberVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {
    static void memberModify(MemberVO mvo) {
    }

    int insert(MemberVO mvo);

    int insertAuth(String email);

    MemberVO selectEmail(String username);

    List<AutoVO> selectAuths(String username);

    List<MemberVO> getList();

    void memberPwdModify(MemberVO mvo);
}
