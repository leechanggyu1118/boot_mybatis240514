package com.ezen.www.service;

import com.ezen.www.domain.MemberVO;

import java.util.List;

public interface MemberService {
    int insert(MemberVO mvo);

    List<MemberVO> getList();

    void memberModify(MemberVO mvo);

    void memberPwdModify(MemberVO mvo);

    void memberAuthDelete(String email);

    void memberDelete(String email);
}
