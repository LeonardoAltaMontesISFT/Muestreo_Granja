package com.blacmircrosystems.Peso_granja.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FlockHouseMortalityResponse {
    private Long  totalMortality;
    private Long   acummulatedMaleDeaths;
    private Long   acummulatedFemaleDeaths;
    private Long idFlockHouse;
    private String numberPoultry;

}
