package com.blacmircrosystems.Peso_granja.dto.response;

import com.blacmircrosystems.Peso_granja.enums.SamplingStatus;
import com.blacmircrosystems.Peso_granja.enums.Sex;
import com.blacmircrosystems.Peso_granja.enums.Zone;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@AllArgsConstructor
public class SamplingResponse {
    private Long id;

    private Long poultryHouseId;

    private String poultryHouseNumber;

    private int ageBirds;

    private Sex sex;

    private Zone zone;

    private SamplingStatus status;

    private int amountBirds;

    private double totalWeight;

    private double averageWeight;

    private double minimumWeight;

    private double maximumWeight;

    private LocalDateTime createdDate;
}
