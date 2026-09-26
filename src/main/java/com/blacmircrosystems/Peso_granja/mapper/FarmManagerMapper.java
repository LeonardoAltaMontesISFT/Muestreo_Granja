package com.blacmircrosystems.Peso_granja.mapper;

import com.blacmircrosystems.Peso_granja.dto.request.FarmManagerRequest;
import com.blacmircrosystems.Peso_granja.dto.response.FarmManagerResponse;
import com.blacmircrosystems.Peso_granja.entity.FarmManager;
import org.springframework.stereotype.Component;

@Component
public class FarmManagerMapper {
    public FarmManager toEntity(FarmManagerRequest request){
        FarmManager manager= new FarmManager();
        manager.setName(request.getName());
        manager.setLastName(request.getLastName());
        manager.setAge(request.getAge());
        manager.setEmail(request.getEmail());
        manager.setPhone(request.getPhone());
        return manager;
    }
    public FarmManagerResponse toResponse(FarmManager manager){
        FarmManagerResponse response= new FarmManagerResponse();
        response.setName(manager.getName());
        response.setLastName(manager.getLastName());
        response.setAge(manager.getAge());
        response.setId(manager.getId());
        response.setPhone(manager.getPhone());
        response.setNameVeterinarian(manager.getVeterinarian().getName());
        response.setRole(manager.getUserAccount().getRole().getName().name());
        response.setUsername(manager.getUserAccount().getUsername());

        return response;
    }
}
