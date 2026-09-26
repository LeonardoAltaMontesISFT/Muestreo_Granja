package com.blacmircrosystems.Peso_granja.controller;

import com.blacmircrosystems.Peso_granja.dto.request.VeterinarianRequest;
import com.blacmircrosystems.Peso_granja.dto.response.VeterinarianResponse;
import com.blacmircrosystems.Peso_granja.entity.Veterinarian;
import com.blacmircrosystems.Peso_granja.service.VeterinarianService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/veterinario")
public class VeterinarianController {
    private final VeterinarianService service;
    @PostMapping()
    public ResponseEntity<VeterinarianResponse> create(@RequestBody VeterinarianRequest request){
        VeterinarianResponse response = service.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @GetMapping("/todos")
    public ResponseEntity<List<VeterinarianResponse>> getAll(){
        return ResponseEntity.ok(service.getAll());
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
