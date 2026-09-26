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
@Table(name = "veterinarian")
public class Veterinarian {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private Integer age;
    @Column(nullable = false)
    private String professionalLicense;
    @Column(nullable = false)
    private String phone;
    @CreationTimestamp
    @Column(nullable = false,updatable = false)
    private LocalDateTime registrationDate;
    @Column(nullable = false)
    private String email;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_account_id",nullable = false,unique = true)
    private UserAccount userAccount;

}
