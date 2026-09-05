package com.blacmircrosystems.Peso_granja.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "mortalityy")
public class    Mortality {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer maleDeaths;

    @Column(nullable = false)
    private Integer femaleDeaths;

    @CreationTimestamp
    @Column(nullable = false,updatable = false)
    private LocalDateTime recordAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flock_house_id", nullable = false)
    private FlockHouse flockHouse;



}
