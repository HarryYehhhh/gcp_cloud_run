package com.example.gcptest.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import com.example.gcptest.entity.Member;
import com.example.gcptest.repository.MemberRepository;

@Component
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private MemberRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {

        Member member = userRepository.findByAccount(account);

        if(member == null) {
            throw new UsernameNotFoundException("User not found with account: " + account);
        }else {
            String userAccount = member.getAccount();
            String userPassword = member.getPassword();
            List<GrantedAuthority> authorities = new ArrayList<>();
            return new User(userAccount, userPassword, authorities);
        }
    }
}
