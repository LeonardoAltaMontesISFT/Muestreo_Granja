package com.blacmircrosystems.Peso_granja.service;

import com.blacmircrosystems.Peso_granja.dto.request.MortalityRequest;
import com.blacmircrosystems.Peso_granja.dto.response.FlockHouseMortalityResponse;
import com.blacmircrosystems.Peso_granja.dto.response.MortalityResponse;
import com.blacmircrosystems.Peso_granja.entity.FlockHouse;
import com.blacmircrosystems.Peso_granja.entity.Mortality;
import com.blacmircrosystems.Peso_granja.mapper.MortalityMapper;
import com.blacmircrosystems.Peso_granja.repository.FlockHouseRepository;
import com.blacmircrosystems.Peso_granja.repository.MortalityRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import com.blacmircrosystems.Peso_granja.repository.MortalityTotal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class MortalityService {
    private  final MortalityRepository repository;
    private final MortalityMapper mapper;
    private final FlockHouseRepository flockHouseRepository;


    //Creacion de un registro de mortalidad
    public MortalityResponse create(MortalityRequest request){
        FlockHouse flockHouse= findFlockHouseId(request.getIdFlockHouse());
        Mortality mortality = mapper.toEntity(request);
        mortality.setFlockHouse(flockHouse);
        LocalDateTime recordAt= LocalDateTime.now();
        mortality.setRecordAt(recordAt);
        long age= ChronoUnit.DAYS.between(flockHouse.getRecordAt().toLocalDate(),recordAt.toLocalDate());
        mortality.setAge((int) age);
        Mortality saved;
        saved = repository.save(mortality);
        return mapper.toResponse(saved);
    }
    //Metodo para mortalidad acumulada
    public FlockHouseMortalityResponse getMortatlityTotal(Long id){
        FlockHouse flockHouse = findFlockHouseId(id);
        MortalityTotal mortalityTotal = repository.getTotalsByFlockHouseId(flockHouse.getId());

        long total = mortalityTotal.getFemaleDeaths() + mortalityTotal.getMaleDeaths();
        long femaleDeaths = mortalityTotal.getFemaleDeaths();;
        long maleDetahs = mortalityTotal.getMaleDeaths();
        return  new FlockHouseMortalityResponse(total,maleDetahs,femaleDeaths,flockHouse.getId(),flockHouse.getPoultryHouse().getNumberPoultry());


    }

    //Metodo para eliminar un registro de mortalidad
    public void delete(Long id){
        Mortality mortality = findMortalityId(id);
        repository.delete(mortality);
    }
    //Metodo para modificar
    public MortalityResponse update (Long id,MortalityRequest request){
        Mortality mortality = findMortalityId(id);
        mortality.setFlockHouse(findFlockHouseId(request.getIdFlockHouse()));
        mortality.setFemaleDeaths(request.getFemaleDeaths());
        mortality.setMaleDeaths(request.getMaleDeaths());
        Mortality save = repository.save(mortality);
        return mapper.toResponse(save);
    }
    //Meotodo para listar todas mortalidades de una caseta
    public List<MortalityResponse> getByFlockHouse(Long id){
        FlockHouse flockHouse = findFlockHouseId(id);
        List<Mortality> mortalities=  repository.findByFlockHouseId(flockHouse.getId());
        return  mortalities.stream().map(mapper::toResponse).toList();
    }
    //Metodo para cantidad total de mortalidad por parvada

    private Mortality findMortalityId(Long id){
        return repository.findById(id).orElseThrow(()-> new RuntimeException("Registro de mortalidad no existente"));
    }
    private FlockHouse findFlockHouseId(Long id){
        return flockHouseRepository.findById(id).orElseThrow(()-> new RuntimeException("Parvada en caseta no encontrada"));
    }
}
