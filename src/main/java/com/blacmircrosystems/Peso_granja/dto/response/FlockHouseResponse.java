package com.blacmircrosystems.Peso_granja.dto.response;

import com.blacmircrosystems.Peso_granja.entity.Flock;
import com.blacmircrosystems.Peso_granja.entity.PoultryHouse;
import com.blacmircrosystems.Peso_granja.enums.FlockHouseStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FlockHouseResponse {
    private Long id;
    private Integer initialFemaleBirdCount;
    private Integer initialMaleBirdCount;
    private LocalDateTime removalDate;
    private FlockHouseStatus status;
    private Long  idFlock;
    private Long  idPoultryHouse;
    private String codeFlock;
    private String numberPoultryHose;
    private  Integer currentMaleCount;
    private Integer currentFemaleCount;
    private Integer acummulatedFemaleDeaths;
    private Integer accumulatedMaleDeaths;
}
