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
@Table(name = "poultry_house",
        uniqueConstraints = {
        @UniqueConstraint(
                name = "uk_poultry_house_farm_number",
                columnNames = {"farm_id", "number_poultry"}        )
        })
public class PoultryHouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Column(name = "number_poultry",nullable = false)
    private  String numberPoultry;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "farm_id",nullable = false)
    private Farm farm;
}
