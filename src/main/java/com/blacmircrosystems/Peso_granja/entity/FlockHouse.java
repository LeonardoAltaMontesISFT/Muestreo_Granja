package com.blacmircrosystems.Peso_granja.entity;

import com.blacmircrosystems.Peso_granja.enums.FlockHouseStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.print.attribute.IntegerSyntax;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "flock_house",uniqueConstraints = {
        @UniqueConstraint(name = "uk_flock_poultry_house",columnNames = {
                "flock_id","poultry_house_id"
        })
})
public class FlockHouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Integer liveBirdCount;
    @Column(nullable = false)
    private Integer initialMaleBirdCount;
    @Column(nullable = false)
    private Integer initialFemaleBirdCount;
    @Column(nullable = false)
    private Integer currentMaleCount;
    @Column(nullable = false)
    private Integer currentFemaleCount;
    @Column(nullable = false)
    private Integer acummulatedMaleDeaths;
    @Column(nullable = false)
    private Integer acummulatedFemaleDeaths;

    private LocalDate removalDate;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false,length = 20)
    private FlockHouseStatus status;
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "flock_id")
    private Flock flock;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "poultry_house_id")
    private PoultryHouse poultryHouse;


}
