package com.blacmircrosystems.Peso_granja.controller;

import com.blacmircrosystems.Peso_granja.dto.request.FlockHouseRequest;
import com.blacmircrosystems.Peso_granja.dto.response.FlockHouseResponse;
import com.blacmircrosystems.Peso_granja.entity.FlockHouse;
import com.blacmircrosystems.Peso_granja.service.FlockHouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("api/parvadacaseta")
public class FlockHouseController {
    private final  FlockHouseService service;

    //Create
    @PostMapping
    public ResponseEntity<FlockHouseResponse> create(@RequestBody FlockHouseRequest request){
        FlockHouseResponse response = service.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    //Listar todos
    @GetMapping
    public ResponseEntity<List<FlockHouseResponse>> getAll(){
        return ResponseEntity.ok(service.getAll());
    }
    //Listar por parvada especifica
    @GetMapping("/flock/{id}")
    public ResponseEntity<List<FlockHouseResponse>> getByFlock(@PathVariable Long id){
        return ResponseEntity.ok(service.getAllByFlock(id));
    }

    //Buscar por id
    @GetMapping("/{id}")
    public ResponseEntity<FlockHouseResponse> getById(@PathVariable Long id){
        return ResponseEntity.ok(service.getById(id));
    }

}
