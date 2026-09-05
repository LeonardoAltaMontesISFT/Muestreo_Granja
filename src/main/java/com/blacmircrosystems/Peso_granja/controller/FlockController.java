package com.blacmircrosystems.Peso_granja.controller;

import com.blacmircrosystems.Peso_granja.dto.request.FlockRequest;
import com.blacmircrosystems.Peso_granja.dto.response.FlockResponse;
import com.blacmircrosystems.Peso_granja.entity.Flock;
import com.blacmircrosystems.Peso_granja.service.FlockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("api/parvada")
public class FlockController {
    private final FlockService service;
    //Create
    @PostMapping
    public ResponseEntity<FlockResponse> create(@RequestBody FlockRequest request){
        FlockResponse response =  service.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    //Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
    //Listar todos
    @GetMapping("/todos")
    public ResponseEntity<List<FlockResponse>> getAll(){
        return ResponseEntity.ok(service.getAll());
    }
    //Listar por id
    @GetMapping("/{id}")
    public ResponseEntity<FlockResponse> getById(@PathVariable Long id){
        return ResponseEntity.ok(service.getById(id));
    }
    //Update
    @PutMapping("{id}")
    public ResponseEntity<FlockResponse> update(@RequestBody FlockRequest request,@PathVariable Long id){
        FlockResponse change = service.update(request,id);
        return ResponseEntity.ok(change);
    }
}
