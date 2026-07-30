package com.blacmircrosystems.Peso_granja.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PoultryHouseResponse {
    private Long id;
    private String numberPoultry;
    private Long farmId;
    private String nameFarm;
}
