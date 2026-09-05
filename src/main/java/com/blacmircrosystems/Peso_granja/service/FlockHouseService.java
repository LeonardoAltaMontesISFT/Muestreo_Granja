package com.blacmircrosystems.Peso_granja.service;

import com.blacmircrosystems.Peso_granja.dto.request.FlockHouseRequest;
import com.blacmircrosystems.Peso_granja.dto.response.FlockHouseResponse;
import com.blacmircrosystems.Peso_granja.entity.Farm;
import com.blacmircrosystems.Peso_granja.entity.Flock;
import com.blacmircrosystems.Peso_granja.entity.FlockHouse;
import com.blacmircrosystems.Peso_granja.entity.PoultryHouse;
import com.blacmircrosystems.Peso_granja.mapper.FlockHouseMapper;
import com.blacmircrosystems.Peso_granja.repository.FarmRepository;
import com.blacmircrosystems.Peso_granja.repository.FlockHouseRepository;
import com.blacmircrosystems.Peso_granja.repository.FlockRepository;
import com.blacmircrosystems.Peso_granja.repository.PoultryHouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FlockHouseService {
    private final  FlockHouseMapper mapper;
    private final FlockHouseRepository repository;
    private final  FlockRepository flockRepository;
    private final PoultryHouseRepository poultryHouseRepository;
    //Create
    public FlockHouseResponse create(FlockHouseRequest request){
        //Excepciones para verificar si no existe ya registrada una poultryhouse repetida en una parvada
        if(repository.existsByFlockIdAndPoultryHouseId(request.getFlockId(),request.getPoultryHouseId())){
            throw new IllegalArgumentException("El galpon con id:"+ request.getPoultryHouseId() +
                    "no se puede relacionar con la paravada.Cuenta co un registro" +
                    "previo en esta parvada");
        }
        Flock flock= flockById(request.getFlockId());
        PoultryHouse poultryHouse= poultryHouseById(request.getPoultryHouseId());
        FlockHouse flockHouse= mapper.toEntity(request);
        flockHouse.setFlock(flock);
        flockHouse.setPoultryHouse(poultryHouse);
        FlockHouse saved= repository.save(flockHouse);
        return mapper.toResponse(saved);
    }

    public FlockHouseResponse getById(Long id){
        return mapper.toResponse(repository.findById(id).orElseThrow(()-> new RuntimeException("Parvada en caseta no exite")));
    }

    public List<FlockHouseResponse> getAll(){
        List<FlockHouse> list = repository.findAll();
        return list.stream().map(mapper::toResponse).toList();
    }

    public List<FlockHouseResponse> getAllByFlock(Long idFlock){
        Flock flock= flockById(idFlock);
        List<FlockHouse> list = repository.findByFlockId(idFlock);
        return list.stream().map(mapper::toResponse).toList();

    }
    //Update pendiente los parametros y delete no permitido

    private Flock flockById(Long id){
        return flockRepository.findById(id).orElseThrow(()-> new RuntimeException("Parvada no encontrada"));
    }


    private PoultryHouse poultryHouseById(Long id){
        return poultryHouseRepository.findById(id).orElseThrow(()-> new RuntimeException("Numero de caseta no encontrado"));
    }
}
