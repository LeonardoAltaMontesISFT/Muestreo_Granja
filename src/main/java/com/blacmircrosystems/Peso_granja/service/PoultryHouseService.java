package com.blacmircrosystems.Peso_granja.service;

import com.blacmircrosystems.Peso_granja.dto.request.PoultryHouseRequest;
import com.blacmircrosystems.Peso_granja.dto.response.PoultryHouseResponse;
import com.blacmircrosystems.Peso_granja.entity.Farm;
import com.blacmircrosystems.Peso_granja.entity.PoultryHouse;
import com.blacmircrosystems.Peso_granja.mapper.PoultryHouseMapper;
import com.blacmircrosystems.Peso_granja.repository.FarmRepository;
import com.blacmircrosystems.Peso_granja.repository.PoultryHouseRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PoultryHouseService {
    private final PoultryHouseRepository poultryHouseRepository;
    private final PoultryHouseMapper poultryHouseMapper;
    private final FarmRepository farmRepository;
//Crear caseta
    @Transactional
    public PoultryHouseResponse create(PoultryHouseRequest poultryHouseRequest){
        Farm farm = finByIdFarm(poultryHouseRequest.getFarmId());

    /*if (poultryHouseRepository.existsByNumberPoultry(poultryHouseRequest.getNumberPoultry())){
        throw new RuntimeException("Ya existe una caseta con ese numero");
    }*/boolean alreadyExistys = poultryHouseRepository.existsByFarmIdAndNumberPoultry(poultryHouseRequest.getFarmId(),poultryHouseRequest.getNumberPoultry());
    if(alreadyExistys){
        throw new IllegalArgumentException("La caseta numero"+ poultryHouseRequest.getNumberPoultry() +"ya existe en la granja " + farm.getName());
    }
    PoultryHouse poultryHouse = poultryHouseMapper.toEntity(poultryHouseRequest);
    poultryHouse.setFarm(farm);

    PoultryHouse save= poultryHouseRepository.save(poultryHouse);
    return poultryHouseMapper.toResponse(save);
    }
    //Obtener id
    @Transactional
    public PoultryHouseResponse getById(Long id){
        PoultryHouse poultryHouse = findEntityById(id);
        return poultryHouseMapper.toResponse(poultryHouse);
    }

    //Actualizar Caseta
    @Transactional
    public PoultryHouseResponse update(PoultryHouseRequest poultryHouseRequest,Long id){
        Farm farm= finByIdFarm(poultryHouseRequest.getFarmId());
        PoultryHouse existing= findEntityById(id);
        existing.setNumberPoultry(poultryHouseRequest.getNumberPoultry());
        existing.setFarm(farm);
        PoultryHouse poultryHouse = poultryHouseRepository.save(existing);
        return poultryHouseMapper.toResponse(poultryHouse);

    }
    //Eliminar caseta
    @Transactional
    public void delete(Long id){
        PoultryHouse existing = findEntityById(id);
        poultryHouseRepository.deleteById(id);

    }
    //Listar casetas
    public List<PoultryHouseResponse> getAll(){
        return poultryHouseRepository.findAll().stream().map(poultryHouseMapper::toResponse).toList();
    }
    private PoultryHouse findEntityById(Long id){
        return poultryHouseRepository.findById(id).orElseThrow(() -> new RuntimeException(
                "No existe una caseta con este numero"+ id
        ));
    }
    @Transactional
    public List<PoultryHouseResponse> getByFarm(Long idFarm){
        return poultryHouseRepository.findByFarmId(idFarm).stream().map(poultryHouseMapper::toResponse).toList();
    }
    private Farm finByIdFarm(Long id){
        return farmRepository.findById(id).orElseThrow(()-> new RuntimeException("No existe una granja con el id"+ id));
    }
}
