package com.blacmircrosystems.Peso_granja.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_account")
public class   UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,unique = true)
    private String username;
    @Column(nullable = false)
    private String passwordHash;
    @Column(nullable = false)
    private boolean enabled = true;
    @CreationTimestamp()
    @Column(updatable = false,nullable = false)
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "role_id",nullable = false)
    private Role role;

}
