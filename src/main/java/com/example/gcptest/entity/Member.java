package com.example.gcptest.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uid")
    private Integer uid;
    
    @Column(name = "account", nullable = false, unique = true, length = 255)
    private String account;
    
    @Column(name = "password", nullable = false, length = 255)
    private String password;
    
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
    
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "user_has_role",
        joinColumns = @JoinColumn(name = "uid"),
        inverseJoinColumns = @JoinColumn(name = "rid")
    )
    private Set<Role> roles = new HashSet<>();
    
    public Member(String account, String password) {
        this.account = account;
        this.password = password;
    }
    
    public void addRole(Role role) {
        this.roles.add(role);
        role.getUsers().add(this);
    }
    
    public void removeRole(Role role) {
        this.roles.remove(role);
        role.getUsers().remove(this);
    }
}
