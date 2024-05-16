package com.ezen.www.domain;

import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MemberVO {
    private String email;
    private String pwd;
    private String nickName;
    private String regAt;
    private String lastLogin;
    private List<AutoVO> authList;


}
