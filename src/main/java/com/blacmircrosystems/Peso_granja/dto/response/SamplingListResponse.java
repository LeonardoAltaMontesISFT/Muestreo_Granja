package com.blacmircrosystems.Peso_granja.dto.response;

import com.blacmircrosystems.Peso_granja.enums.Sex;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SamplingListResponse {
    private Long id;
    private int ageBirds;
    private Sex sex;
}
