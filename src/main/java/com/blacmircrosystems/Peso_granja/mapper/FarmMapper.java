package com.blacmircrosystems.Peso_granja.mapper;

import com.blacmircrosystems.Peso_granja.dto.request.FarmRequest;
import com.blacmircrosystems.Peso_granja.dto.response.FarmResponse;
import com.blacmircrosystems.Peso_granja.entity.Farm;
import org.springframework.stereotype.Component;

@Component
public class FarmMapper {
    public Farm toEntity(FarmRequest request){
        Farm farm = new Farm();
        farm.setName(request.getName());
        farm.setUbicacion(request.getUbicacion());
        return farm;
    }
    public FarmResponse toResponse(Farm farm){
        return new FarmResponse(farm.getId(), farm.getName(), farm.getUbicacion());
    }
}
