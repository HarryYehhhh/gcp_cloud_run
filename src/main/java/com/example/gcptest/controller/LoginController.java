package com.example.gcptest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.gcptest.entity.Member;
import com.example.gcptest.repository.MemberRepository;

@RestController
public class LoginController {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/hello")
    public String hello() {
        return "Hello Blog!";
    }

    @PostMapping("/register")
    public Member register(@RequestBody Member member) {
        String hashedPassword = passwordEncoder.encode(member.getPassword());
        member.setPassword(hashedPassword);
        
        memberRepository.save(member);
        return member;
    }
}
