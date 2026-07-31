package com.blacmircrosystems.Peso_granja.service;

import com.blacmircrosystems.Peso_granja.dto.request.SamplingRequest;
import com.blacmircrosystems.Peso_granja.dto.response.SamplingListResponse;
import com.blacmircrosystems.Peso_granja.dto.response.SamplingResponse;
import com.blacmircrosystems.Peso_granja.entity.PoultryHouse;
import com.blacmircrosystems.Peso_granja.entity.Sampling;
import com.blacmircrosystems.Peso_granja.mapper.SamplingMapper;
import com.blacmircrosystems.Peso_granja.repository.PoultryHouseRepository;
import com.blacmircrosystems.Peso_granja.repository.SamplingRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SamplingService {
private final SamplingRepository samplingRepository;
private final PoultryHouseRepository poultryHouseRepository;
private final SamplingMapper samplingMapper;

//Listar muestreos
    @Transactional
    public List<SamplingResponse> getAll(){
        return samplingRepository.findAllByOrderByCreatedDateDesc().stream().map(samplingMapper::toResponse).toList();
    }
    //Listar por caseta
    @Transactional
    public List<SamplingListResponse> getByPoultryHouseId(Long id){
        return samplingRepository.findByPoultryHouseId(id).stream().map(samplingMapper::toResponseList).toList();
    }
    //Buscar por id
    public SamplingResponse getById(Long id){
        Sampling sampling = findEntityById(id);
        return samplingMapper.toResponse(sampling);
    }
    //Crear muestreo
    public SamplingResponse create(SamplingRequest sampling){

        PoultryHouse poultryHouse= findPoultryHouseById(sampling.getPoultryHouseId());
        Sampling sampling1= samplingMapper.toEntity(sampling);
        sampling1.setPoultryHouse(poultryHouse);
        Sampling saved= samplingRepository.save(sampling1);
        return samplingMapper.toResponse(saved);

    }
    //Actualizar muestreo
    public SamplingResponse update(Long id, SamplingRequest request){
        Sampling  existing=findEntityById(id);
        PoultryHouse poultryHouse= findPoultryHouseById(request.getPoultryHouseId());
        existing.setPoultryHouse(poultryHouse);
        existing.setAgeBirds(request.getAgeBirds());
        existing.setSex(request.getSex());
        existing.setZone(request.getZone());
        Sampling saved = samplingRepository.save(existing);
        return samplingMapper.toResponse(saved);
    }
    //Eliminar muestreo
    public void delete(Long id){
    Sampling sampling = findEntityById(id);
    samplingRepository.delete(sampling);

    }

    private Sampling findEntityById(Long id){
    return samplingRepository.findById(id).orElseThrow(()-> new RuntimeException("Muestra no encontrada con el id" + id));
    }
    private PoultryHouse findPoultryHouseById(Long id){
    return poultryHouseRepository.findById(id).orElseThrow(()-> new RuntimeException("No se encontro la caseta con ese id : " +id));
    }
}
