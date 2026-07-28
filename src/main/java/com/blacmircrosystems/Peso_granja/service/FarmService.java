package com.blacmircrosystems.Peso_granja.service;

import com.blacmircrosystems.Peso_granja.dto.request.FarmRequest;
import com.blacmircrosystems.Peso_granja.dto.response.FarmResponse;
import com.blacmircrosystems.Peso_granja.entity.Farm;
import com.blacmircrosystems.Peso_granja.mapper.FarmMapper;
import com.blacmircrosystems.Peso_granja.repository.FarmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FarmService {

    private final FarmRepository farmRepository;
    private final FarmMapper farmMapper;
    public FarmResponse create(FarmRequest farm){
        if(farmRepository.existsByName(farm.getName())){
            throw  new RuntimeException("Ya existe una granja con el nombre:" +farm.getName());
        }
        Farm farm1 = farmMapper.toEntity(farm);
        Farm save = farmRepository.save(farm1);
        return  farmMapper.toResponse(save);
    }
    public List<FarmResponse> getAll(){
        return  farmRepository.findAll().stream().map(farmMapper::toResponse).toList();
    }
    public FarmResponse getById(Long id){
        Farm farm = farmRepository.getReferenceById(id);
        return farmMapper.toResponse(farm);
    }
    public FarmResponse update(FarmRequest request, Long id){
        Farm farm = finByEntityId(id);
        farm.setName(request.getName());
        farm.setUbicacion(request.getUbicacion());
        Farm save = farmRepository.save(farm);
        return  farmMapper.toResponse(save);

    }
    public Farm finByEntityId(Long id){
        return  farmRepository.findById(id).orElseThrow(()-> new RuntimeException("No existe granja"));
    }
    public void delete(Long id){
        Farm farm = finByEntityId(id);
        farmRepository.delete(farm);
    }

}
