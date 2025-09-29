package com.example.gcptest.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gcptest.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByAccount(String account);
    boolean existsByAccount(String account);
}