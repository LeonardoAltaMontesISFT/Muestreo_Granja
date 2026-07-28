package com.blacmircrosystems.Peso_granja.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "poultry_house")
public class PoultryHouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    private  String numberPoultry;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "farm_id",nullable = false)
    private Farm farm;
}
