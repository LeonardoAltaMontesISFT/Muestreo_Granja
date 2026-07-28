package com.blacmircrosystems.Peso_granja.dto.request;

import com.blacmircrosystems.Peso_granja.enums.Sex;
import com.blacmircrosystems.Peso_granja.enums.Zone;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SamplingRequest {
    private Long poultryHouseId;
    private int ageBirds;
    private Sex sex;
    private Zone zone;
}
