package com.blacmircrosystems.Peso_granja.entity;

import com.blacmircrosystems.Peso_granja.enums.SamplingStatus;
import com.blacmircrosystems.Peso_granja.enums.Sex;
import com.blacmircrosystems.Peso_granja.enums.Zone;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="sampling")
public class    Sampling {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //Caseta
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="flock_house_id")
    private FlockHouse flockHouse;
    //Cantidad de aves
    @Column(nullable = false)
    private int amountBirds;
    //Edad del ave
    @Column(nullable = false)
    private int ageBirds;
    //Total de peso
    private double totalWeight;
    //PesoPromedio
    @Column(nullable = false)
    private double averageWeight;
    //Fecha
    @CreationTimestamp
    @Column(updatable = false,name = "created_date")
    private LocalDateTime createdDate;
    //Sexo
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Sex sex;
    //Zona
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Zone zone;
    //Estado del muestreo
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SamplingStatus samplingStatus;
    //Peso menor
    @Column(nullable = false)
    private double minimumWeight;
    //Peso Maximo
    @Column(nullable = false)
    private double maximumWeight;
    //Ganancia




}
