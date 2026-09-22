package com.blacmircrosystems.Peso_granja.mapper;

import com.blacmircrosystems.Peso_granja.dto.request.MortalityRequest;
import com.blacmircrosystems.Peso_granja.dto.response.FlockHouseMortalityResponse;
import com.blacmircrosystems.Peso_granja.dto.response.MortalityResponse;
import com.blacmircrosystems.Peso_granja.entity.Mortality;
import com.blacmircrosystems.Peso_granja.repository.MortalityTotal;
import org.springframework.stereotype.Component;

@Component
public class MortalityMapper {
    public Mortality toEntity(MortalityRequest request){
        Mortality mortality = new Mortality();
        mortality.setMaleDeaths(request.getMaleDeaths());
        mortality.setFemaleDeaths(request.getFemaleDeaths());
        return mortality;
    }

    public MortalityResponse toResponse(Mortality mortality){
        MortalityResponse response = new MortalityResponse();
        response.setFemaleDeaths(mortality.getFemaleDeaths());
        response.setMaleDeaths(mortality.getMaleDeaths());
        response.setId(mortality.getId());
        response.setRecordAt(mortality.getRecordAt());
        response.setNumberPoutlry(mortality.getFlockHouse().getPoultryHouse().getNumberPoultry());
        return response;
    }
}
