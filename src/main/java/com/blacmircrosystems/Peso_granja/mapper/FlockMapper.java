package com.blacmircrosystems.Peso_granja.mapper;

import com.blacmircrosystems.Peso_granja.dto.request.FlockRequest;
import com.blacmircrosystems.Peso_granja.dto.response.FlockResponse;
import com.blacmircrosystems.Peso_granja.entity.Flock;
import org.springframework.stereotype.Component;

@Component
public class FlockMapper{
    public Flock toEntity(FlockRequest request){
        Flock flock= new Flock();
        flock.setCode(request.getCode());
        flock.setStatus(request.getStatus());
     return flock;
    }
    public FlockResponse toResponse(Flock flock){
        return new FlockResponse(flock.getId(), flock.getCode(), flock.getStatus(),flock.getFarm().getName(),flock.getStartTime(),flock.getCloseTime());
    }
}
