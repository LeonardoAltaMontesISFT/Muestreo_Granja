package com.blacmircrosystems.Peso_granja.controller;

import com.blacmircrosystems.Peso_granja.dto.request.FarmManagerRequest;
import com.blacmircrosystems.Peso_granja.dto.response.FarmManagerResponse;
import com.blacmircrosystems.Peso_granja.entity.FarmManager;
import com.blacmircrosystems.Peso_granja.service.FarmManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("api/encargado")
public class FarmManagerController {

    private final FarmManagerService service;

    @PostMapping
    public ResponseEntity<FarmManagerResponse> create(@RequestBody FarmManagerRequest request){
        FarmManagerResponse response = service.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @DeleteMapping("/{id}")
    public  ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/todos")
    public ResponseEntity<List<FarmManagerResponse>> getAll(){
        List<FarmManagerResponse> list = service.getAll();
        return ResponseEntity.ok(list);
    }

}
