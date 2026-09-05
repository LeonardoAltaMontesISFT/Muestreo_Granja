package com.blacmircrosystems.Peso_granja.controller;

import com.blacmircrosystems.Peso_granja.dto.request.SamplingRequest;
import com.blacmircrosystems.Peso_granja.dto.response.SamplingListResponse;
import com.blacmircrosystems.Peso_granja.dto.response.SamplingResponse;
import com.blacmircrosystems.Peso_granja.entity.Sampling;
import com.blacmircrosystems.Peso_granja.service.SamplingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/muestreos")
public class SamplingController {
    private final SamplingService samplingService;

    @GetMapping
    public ResponseEntity<List<SamplingResponse>>getAll(){
        List<SamplingResponse> samplings =  samplingService.getAll();
        return ResponseEntity.ok(samplings);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SamplingResponse>getById(@PathVariable Long id){
        SamplingResponse sampling = samplingService.getById(id);
        return ResponseEntity.ok(sampling);
    }
    @GetMapping("/casetas/{casetaId}")
    public ResponseEntity<List<SamplingListResponse>> getAllPoultryHouse(@PathVariable Long casetaId){
        return ResponseEntity.ok(samplingService.getByFlockHouseId(casetaId));
    }

    @PostMapping
    public ResponseEntity<SamplingResponse> create(@RequestBody SamplingRequest sampling){
        SamplingResponse newSampling =  samplingService.create(sampling);
        return ResponseEntity.status(HttpStatus.CREATED).body(newSampling);
    }
    @PutMapping("/{id}")
    public ResponseEntity<SamplingResponse> update(@PathVariable Long id, @RequestBody SamplingRequest sampling){
        return ResponseEntity.ok(samplingService.update(id,sampling));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>  delete(@PathVariable Long id){
         samplingService.delete(id);
         return ResponseEntity.noContent().build();
    }





}
