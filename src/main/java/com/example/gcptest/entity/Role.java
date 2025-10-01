package com.example.gcptest.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "role")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    @Id
    @Column(name = "rid", length = 50)
    private String rid;
    
    @Column(name = "role_name", nullable = false, unique = true, length = 100)
    private String roleName;
    
    @Column(name = "description", length = 255)
    private String description;
    
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
    
    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private Set<Member> users = new HashSet<>();
    
    public Role(String rid, String roleName, String description) {
        this.rid = rid;
        this.roleName = roleName;
        this.description = description;
    }
}
