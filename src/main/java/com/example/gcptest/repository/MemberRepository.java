package com.example.gcptest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gcptest.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Integer> {
    Member findByAccount(String account);
    boolean existsByAccount(String account);
}