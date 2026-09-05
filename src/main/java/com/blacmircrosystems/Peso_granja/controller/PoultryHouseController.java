package com.blacmircrosystems.Peso_granja.controller;

import com.blacmircrosystems.Peso_granja.dto.request.PoultryHouseRequest;
import com.blacmircrosystems.Peso_granja.dto.response.PoultryHouseResponse;
import com.blacmircrosystems.Peso_granja.entity.PoultryHouse;
import com.blacmircrosystems.Peso_granja.service.PoultryHouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/casetas")
public class PoultryHouseController {
    private final PoultryHouseService poultryHouseService;

    @GetMapping
    ResponseEntity<List<PoultryHouseResponse>> getAll(){
        List<PoultryHouseResponse> poultryHouses = poultryHouseService.getAll();
        return  ResponseEntity.ok(poultryHouses);
    }
    @GetMapping("/{id}")
    public ResponseEntity<PoultryHouseResponse> getById(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(
                poultryHouseService.getById(id)
        );
    }

    @PostMapping
    public ResponseEntity<PoultryHouseResponse> create(@RequestBody PoultryHouseRequest poultryHouse){
    PoultryHouseResponse poultryHouse1 = poultryHouseService.create(poultryHouse);
    return ResponseEntity.status(HttpStatus.CREATED).body(poultryHouse1);
    }
    @PutMapping("/{id}")
    public ResponseEntity<PoultryHouseResponse> update(@PathVariable Long id, @RequestBody PoultryHouseRequest poultryHouse){
        PoultryHouseResponse change = poultryHouseService.update(poultryHouse,id);
        return  ResponseEntity.ok(change);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<PoultryHouseResponse> delete(@PathVariable Long id ){
        poultryHouseService.delete(id);
        return ResponseEntity.noContent().build();

    }
    @GetMapping("/granja/{idFarm}")
    public ResponseEntity<List<PoultryHouseResponse>> getIdFarm(@PathVariable Long idFarm){
        return ResponseEntity.ok(poultryHouseService.getByFarm(idFarm));
    }


}
