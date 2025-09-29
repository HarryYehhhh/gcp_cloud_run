package com.example.gcptest.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gcptest.entity.Role;
import com.example.gcptest.entity.User;
import com.example.gcptest.repository.RoleRepository;
import com.example.gcptest.repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private RoleRepository roleRepository;
    
    // 建立使用者並分配角色
    public User createUser(String account, String password, String roleId) {
        User user = new User(account, password);
        
        Role role = roleRepository.findById(roleId)
            .orElseThrow(() -> new RuntimeException("Role is not exist"));
        
        user.addRole(role);
        
        return userRepository.save(user);
    }
    
    // 查詢使用者的所有角色
    public Set<Role> getUserRoles(Integer uid) {
        User user = userRepository.findById(uid)
            .orElseThrow(() -> new RuntimeException("Role is not exist"));
        
        return user.getRoles();
    }
}
