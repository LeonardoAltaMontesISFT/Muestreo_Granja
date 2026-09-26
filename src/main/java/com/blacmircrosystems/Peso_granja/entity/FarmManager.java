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
@Table(name = "farm_manager")
public class FarmManager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private Integer age;
    @Column(nullable = false,length = 10)
    private String phone;
    @Column(nullable = false)
    private String email;
    @CreationTimestamp
    @Column(nullable = false,updatable = false)
    private LocalDateTime registrationDate;
    @OneToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "user_account_id",nullable = false,unique = true)
    private UserAccount userAccount;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "veterinarian_id")
    private Veterinarian veterinarian;
}
