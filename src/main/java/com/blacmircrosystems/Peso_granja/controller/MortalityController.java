package com.blacmircrosystems.Peso_granja.controller;

import com.blacmircrosystems.Peso_granja.dto.request.MortalityRequest;
import com.blacmircrosystems.Peso_granja.dto.response.FlockHouseMortalityResponse;
import com.blacmircrosystems.Peso_granja.dto.response.MortalityResponse;
import com.blacmircrosystems.Peso_granja.entity.Mortality;
import com.blacmircrosystems.Peso_granja.service.MortalityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("api/mortalidad")
public class MortalityController {
    private final MortalityService mortalityService;
    @PostMapping
    public ResponseEntity<MortalityResponse> create (@RequestBody MortalityRequest request){
        MortalityResponse response = mortalityService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @GetMapping("/caseta/{id}")
    public ResponseEntity<List<MortalityResponse>> getByFlockHouse(@PathVariable Long id){
        List<MortalityResponse> list = mortalityService.getByFlockHouse(id);
        return ResponseEntity.ok(list);
    }
    @GetMapping("/totalparvada/{id}")
    public  ResponseEntity<FlockHouseMortalityResponse> getMortalityTotal(@PathVariable Long id){
        FlockHouseMortalityResponse list = mortalityService.getMortatlityTotal(id);
        return ResponseEntity.ok(list);
    }
    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id){
     mortalityService.delete(id);
     return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    ResponseEntity<MortalityResponse> update(@PathVariable Long id, @RequestBody MortalityRequest request){
        MortalityResponse response = mortalityService.update(id,request);
        return ResponseEntity.ok(response);
    }


}
