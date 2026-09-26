package com.blacmircrosystems.Peso_granja.controller;

import com.blacmircrosystems.Peso_granja.dto.request.AdministratorContactRequest;
import com.blacmircrosystems.Peso_granja.dto.request.AdministratorRequest;
import com.blacmircrosystems.Peso_granja.dto.response.AdministratorResponse;
import com.blacmircrosystems.Peso_granja.entity.Administrator;
import com.blacmircrosystems.Peso_granja.service.AdministratorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {
    private final AdministratorService service;
    @GetMapping("/check")
    public ResponseEntity<String> checkAccess(){
        return ResponseEntity.ok("Accesso a administrador autorizado");
    }
    @PostMapping("/administrators")
    public ResponseEntity<AdministratorResponse> create(@Valid @RequestBody AdministratorRequest request){
        AdministratorResponse reponse= service.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(reponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<AdministratorResponse> update(@PathVariable Long id, @RequestBody AdministratorContactRequest request){
        AdministratorResponse response = service.update(id,request);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/todos")
    public ResponseEntity<List<AdministratorResponse>> getAlll(){
        List<AdministratorResponse> list = service.getAll();
        return ResponseEntity.ok(list);
    }


}
