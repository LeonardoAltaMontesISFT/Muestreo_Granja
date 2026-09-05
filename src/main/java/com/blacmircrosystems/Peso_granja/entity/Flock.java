package com.blacmircrosystems.Peso_granja.entity;

import com.blacmircrosystems.Peso_granja.enums.FlockStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "flock")
public class Flock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //Codigo para identificacion de parvada
    @Column(nullable = false,unique = true,length = 50)
    private String code;
    //fecha de inicio
    @CreationTimestamp
    @Column(nullable = false)
    private LocalDate startTime;
    @CreationTimestamp
    private LocalDate createdDate;
    //fecha de cierre
    private LocalDate closeTime;
    //estado de la parvada>
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FlockStatus status;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name = "farm_id")
    private Farm farm;
}
