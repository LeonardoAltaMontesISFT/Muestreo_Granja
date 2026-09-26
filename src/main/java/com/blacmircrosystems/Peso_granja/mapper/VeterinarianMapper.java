package com.blacmircrosystems.Peso_granja.mapper;

import com.blacmircrosystems.Peso_granja.dto.request.VeterinarianRequest;
import com.blacmircrosystems.Peso_granja.dto.response.VeterinarianResponse;
import com.blacmircrosystems.Peso_granja.entity.Veterinarian;
import com.blacmircrosystems.Peso_granja.repository.VeterinarianRepository;
import org.springframework.stereotype.Component;

@Component
public class VeterinarianMapper {
    public Veterinarian toEntity(VeterinarianRequest request){
        Veterinarian veterinarian = new Veterinarian();
        veterinarian.setName(request.getName());
        veterinarian.setLastName(request.getLastName());
        veterinarian.setAge(request.getAge());
        veterinarian.setEmail(request.getEmail());
        veterinarian.setPhone(request.getPhone());
        veterinarian.setProfessionalLicense(request.getProfessionalLicense());
        return veterinarian;
    }
    public VeterinarianResponse toResponse(Veterinarian veterinarian){
        VeterinarianResponse response = new VeterinarianResponse();
        response.setId(veterinarian.getId());
        response.setName(veterinarian.getName());
        response.setLastName(veterinarian.getLastName());
        response.setAge(veterinarian.getAge());
        response.setPhone(veterinarian.getPhone());
        response.setProfessionalLicense(veterinarian.getProfessionalLicense());
        response.setRole(veterinarian.getUserAccount().getRole().getName().name());
        response.setUsername(veterinarian.getUserAccount().getUsername());
        return response;
    }
}
