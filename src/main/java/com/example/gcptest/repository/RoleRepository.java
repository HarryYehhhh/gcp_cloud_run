package com.example.gcptest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gcptest.entity.Role;

public interface RoleRepository extends JpaRepository<Role, String> {
    Role findByRoleName(String roleName);
}
