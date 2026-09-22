package com.blacmircrosystems.Peso_granja.mapper;

import com.blacmircrosystems.Peso_granja.dto.request.AdministratorRequest;
import com.blacmircrosystems.Peso_granja.dto.response.AdministratorResponse;
import com.blacmircrosystems.Peso_granja.entity.Administrator;
import org.springframework.stereotype.Component;

@Component
public class AdministratorMapper {
    public Administrator toEntity(AdministratorRequest request){
        Administrator administrator= new Administrator();
        administrator.setName(request.getName());
        administrator.setLastName(request.getLastName());
        administrator.setAge(request.getAge());
        administrator.setEmail(request.getEmail());
        administrator.setPhone(request.getPhone());
        return administrator;
    }
    public AdministratorResponse toResponse(Administrator administrator){
        AdministratorResponse response= new AdministratorResponse();
        response.setName(administrator.getName());
        response.setLastName(administrator.getLastName());
        response.setAge(administrator.getAge());
        response.setId(administrator.getId());
        response.setPhone(administrator.getPhone());
        response.setRole(administrator.getUserAccount().getRole().getName().name());
        response.setUsername(administrator.getUserAccount().getUsername());
        return response;
    }
}
