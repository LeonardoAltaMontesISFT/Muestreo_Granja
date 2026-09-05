package com.blacmircrosystems.Peso_granja.dto.request;

import com.blacmircrosystems.Peso_granja.entity.Farm;
import com.blacmircrosystems.Peso_granja.enums.FlockStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FlockRequest {
    private String code;
    private FlockStatus status;
    private Long farmId;
}
