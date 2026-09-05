package com.blacmircrosystems.Peso_granja.dto.request;


import com.blacmircrosystems.Peso_granja.entity.FlockHouse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MortalityRequest {
        private Integer maleDeaths;
        private Integer femaleDeaths;
        private FlockHouse flockHouse;

}
