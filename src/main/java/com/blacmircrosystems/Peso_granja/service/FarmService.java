package com.blacmircrosystems.Peso_granja.service;

import com.blacmircrosystems.Peso_granja.dto.request.FarmRequest;
import com.blacmircrosystems.Peso_granja.dto.response.FarmResponse;
import com.blacmircrosystems.Peso_granja.entity.Farm;
import com.blacmircrosystems.Peso_granja.entity.FarmManager;
import com.blacmircrosystems.Peso_granja.entity.UserAccount;
import com.blacmircrosystems.Peso_granja.mapper.FarmMapper;
import com.blacmircrosystems.Peso_granja.repository.FarmManagerRepository;
import com.blacmircrosystems.Peso_granja.repository.FarmRepository;
import com.blacmircrosystems.Peso_granja.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FarmService {

    private final FarmRepository farmRepository;
    private final FarmManagerRepository managerRepository;
    private final FarmMapper farmMapper;
    private final UserAccountRepository userAccountRepository;
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
    public FarmResponse addFarmManager(Long idFarmManager,Long id){
        FarmManager farmManager= managerRepository.findById(idFarmManager).orElseThrow(()-> new RuntimeException("Encargado no encontrado" + id));
        Farm farm = finByEntityId(id);
        farm.setFarmManager(farmManager);
        Farm farm1= farmRepository.save(farm);
        return  farmMapper.toResponse(farm1);
    }
    public FarmResponse quitFarmManager(Long id){
        Farm farm=  finByEntityId(id);
        farm.setFarmManager(null);
        Farm farm1 = farmRepository.save(farm);
        return farmMapper.toResponse(farm1);
    }
    public Farm finByEntityId(Long id){
        return  farmRepository.findById(id).orElseThrow(()-> new RuntimeException("No existe granja"));
    }
    public void delete(Long id){
        Farm farm = finByEntityId(id);
        farmRepository.delete(farm);
    }
    public List<FarmResponse> getByFarmManager(Long idfarmManager){
        List<Farm> list = farmRepository.findByFarmManagerId(idfarmManager);
        return list.stream().map(farmMapper::toResponse).toList();
    }
    public List<FarmResponse> getMyFarms(String name){
        FarmManager manager1 =  managerRepository.findByUserAccountUsername(name);
        List<Farm> list = farmRepository.findByFarmManagerId(manager1.getId());
        return list.stream().map(farmMapper::toResponse).toList();
    }


}
