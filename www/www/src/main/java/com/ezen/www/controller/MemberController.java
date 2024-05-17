package com.ezen.www.controller;

import com.ezen.www.domain.MemberVO;
import com.ezen.www.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/member/*")
@Slf4j
@RequiredArgsConstructor
public class MemberController {
    private final MemberService msv;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public void join(){}

    @PostMapping("/register")
    public String resiter(MemberVO mvo){
        log.info(">>> mvo >>{}", mvo);
        mvo.setPwd(passwordEncoder.encode(mvo.getPwd()));
        int isOk = msv.insert(mvo);

        return "/index";

    }

    @GetMapping("/login")
    public void login(){}

    @GetMapping("/list")
    public void list(Model m){
        m.addAttribute("list", msv.getList());
    }

    @GetMapping("/modify")
    public void modify(){}

    private void logout(HttpServletRequest request, HttpServletResponse response){
        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        new SecurityContextLogoutHandler()
                .logout(request, response, authentication);
    }



    @PostMapping("/modify")
    public String modify(MemberVO mvo, Principal principal,HttpServletRequest request, HttpServletResponse response ){
        String email = principal.getName();
        mvo.setEmail(email);

        if(mvo.getPwd().isEmpty() || mvo.getPwd().length() == 0){
            msv.memberModify(mvo);
        }else {

            String pwdModify = passwordEncoder.encode(mvo.getPwd());
            mvo.setPwd(pwdModify);
            msv.memberPwdModify(mvo);

        }
        logout(request, response);
        return "redirect:/";
    }

    @GetMapping("/deleteMember")
    public String deleteMember(Principal principal, HttpServletRequest request, HttpServletResponse response) {
        String email = principal.getName(); //id

        msv.memberAuthDelete(email);
        msv.memberDelete(email);
        logout(request, response);
        return "redirect:/";
    }






}
