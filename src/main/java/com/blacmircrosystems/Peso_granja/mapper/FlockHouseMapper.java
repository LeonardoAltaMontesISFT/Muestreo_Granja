package com.blacmircrosystems.Peso_granja.mapper;

import com.blacmircrosystems.Peso_granja.dto.request.FlockHouseRequest;
import com.blacmircrosystems.Peso_granja.dto.response.FlockHouseMortalityResponse;
import com.blacmircrosystems.Peso_granja.dto.response.FlockHouseResponse;
import com.blacmircrosystems.Peso_granja.entity.FlockHouse;
import com.blacmircrosystems.Peso_granja.enums.FlockHouseStatus;
import org.springframework.stereotype.Component;

@Component
public class FlockHouseMapper {
    public FlockHouse toEntity(FlockHouseRequest request){
        FlockHouse flockHouse = new FlockHouse();
        flockHouse.setInitialFemaleBirdCount(request.getInitialFemaleBirdCount());
        flockHouse.setInitialMaleBirdCount(request.getInitialMaleBirdCount());
        flockHouse.setStatus(FlockHouseStatus.ACTIVED);
        flockHouse.setLiveBirdCount(request.getInitialFemaleBirdCount()+ request.getInitialMaleBirdCount());
        flockHouse.setCurrentFemaleCount(request.getInitialFemaleBirdCount());
        flockHouse.setCurrentMaleCount(request.getInitialMaleBirdCount());
        return flockHouse;
    }
    public FlockHouseResponse toResponse(FlockHouse flockHouse){
        FlockHouseResponse response = new FlockHouseResponse();
        response.setId(flockHouse.getId());
        response.setInitialFemaleBirdCount(flockHouse.getInitialFemaleBirdCount());
        response.setInitialMaleBirdCount(flockHouse.getInitialMaleBirdCount());
        response.setStatus(flockHouse.getStatus());
        response.setIdFlock(flockHouse.getFlock().getId());
        response.setCodeFlock(flockHouse.getFlock().getCode());
        response.setIdPoultryHouse(flockHouse.getPoultryHouse().getId());
        response.setNumberPoultryHose(flockHouse.getPoultryHouse().getNumberPoultry());
        response.setCurrentFemaleCount(flockHouse.getCurrentFemaleCount());
        response.setCurrentMaleCount(flockHouse.getCurrentMaleCount());

        return response;
    }

}
