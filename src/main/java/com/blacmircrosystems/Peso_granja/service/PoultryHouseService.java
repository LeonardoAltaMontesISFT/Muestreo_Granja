package com.blacmircrosystems.Peso_granja.service;

import com.blacmircrosystems.Peso_granja.dto.request.PoultryHouseRequest;
import com.blacmircrosystems.Peso_granja.dto.response.PoultryHouseResponse;
import com.blacmircrosystems.Peso_granja.entity.PoultryHouse;
import com.blacmircrosystems.Peso_granja.mapper.PoultryHouseMapper;
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
//Crear caseta
    @Transactional
    public PoultryHouseResponse create(PoultryHouseRequest poultryHouseRequest){
    if (poultryHouseRepository.existsByNumberPoultry(poultryHouseRequest.getNumberPoultry())){
        throw new RuntimeException("Ya existe una caseta con ese numero");
    }
    PoultryHouse poultryHouse = poultryHouseMapper.toEntity(poultryHouseRequest);
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
        PoultryHouse existing= findEntityById(id);
        existing.setNumberPoultry(poultryHouseRequest.getNumberPoultry());
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
}
