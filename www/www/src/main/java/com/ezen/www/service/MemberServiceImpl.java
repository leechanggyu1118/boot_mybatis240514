package com.ezen.www.service;

import com.ezen.www.domain.MemberVO;
import com.ezen.www.repository.MemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberMapper memberMapper;

    @Override
    public int insert(MemberVO mvo) {
        int isOk = memberMapper.insert(mvo);
        return (isOk > 0 ? memberMapper.insertAuth(mvo.getEmail()) : 0);
    }

    @Override
    public List<MemberVO> getList() {
        List<MemberVO> list = memberMapper.getList();
        for(MemberVO mvo : list){
            mvo.setAuthList((memberMapper.selectAuths(mvo.getEmail())));
        }
        return list;
    }

    @Override
    public void memberModify(MemberVO mvo) {
        MemberMapper.memberModify(mvo);

    }

    @Override
    public void memberPwdModify(MemberVO mvo) {
        memberMapper.memberPwdModify(mvo);
    }

    @Override
    public void memberAuthDelete(String email) {
        memberMapper.memberAuthDelete(email);
    }

    @Override
    public void memberDelete(String email) {
        memberMapper.memberDelete(email);
    }
}
