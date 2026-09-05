package com.blacmircrosystems.Peso_granja.service;

import com.blacmircrosystems.Peso_granja.dto.request.FlockRequest;
import com.blacmircrosystems.Peso_granja.dto.response.FlockResponse;
import com.blacmircrosystems.Peso_granja.entity.Farm;
import com.blacmircrosystems.Peso_granja.entity.Flock;
import com.blacmircrosystems.Peso_granja.mapper.FlockMapper;
import com.blacmircrosystems.Peso_granja.repository.FarmRepository;
import com.blacmircrosystems.Peso_granja.repository.FlockRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FlockService {
    private final FlockRepository flockRepository;
    private final FlockMapper flockMapper;
    private final FarmRepository farmRepository;

    public FlockResponse create(FlockRequest request){
        Farm farm = farmById(request.getFarmId());
        Flock flock= flockMapper.toEntity(request);
        flock.setFarm(farm);
        Flock saved= flockRepository.save(flock);
        return flockMapper.toResponse(saved);
    }
    @Transactional
    public List<FlockResponse> getAll(){
            return flockRepository.findAllByOrderByCreatedDateDesc().stream().map(flockMapper::toResponse).toList();
    }
    public FlockResponse getById(Long id){
         Flock flock = flockRepository.findById(id).orElseThrow(()-> new RuntimeException("Parvada no encontrada"));
         return flockMapper.toResponse(flock);
        }

    private Farm farmById(Long id){
        return farmRepository.findById(id).orElseThrow(()-> new RuntimeException("Granja no encontrada"));
    }
    public FlockResponse update(FlockRequest request, Long id){
            Flock existing = flockRepository.findById(id).orElseThrow(()-> new RuntimeException("Parvada no encontrada "));
            existing.setStatus(request.getStatus());
            existing.setCode(request.getCode());
            Flock saves = flockRepository.save(existing);
            return flockMapper.toResponse(saves);
    }
    public void delete  (Long id){
        Flock flock = flockRepository.findById(id).orElseThrow(()-> new RuntimeException("Parvada no encontrada"));
        flockRepository.delete(flock);
    }
}
