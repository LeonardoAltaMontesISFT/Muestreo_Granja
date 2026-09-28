package com.blacmircrosystems.Peso_granja.controller;

import com.blacmircrosystems.Peso_granja.dto.request.FarmRequest;
import com.blacmircrosystems.Peso_granja.dto.response.FarmResponse;
import com.blacmircrosystems.Peso_granja.entity.Farm;
import com.blacmircrosystems.Peso_granja.service.FarmService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("api/granja")
public class FarmController {
    private final FarmService farmService;
    @PostMapping
    public ResponseEntity<FarmResponse> create(@RequestBody FarmRequest request){
        FarmResponse farmResponse= farmService.create(request);
        return  ResponseEntity.status(HttpStatus.CREATED).body(farmResponse);
    }
    @PutMapping("/{farmId}/encargado/{managerId}")
    public ResponseEntity<FarmResponse> addManager(
            @PathVariable Long farmId,
            @PathVariable Long managerId
    ) {
        return ResponseEntity.ok(farmService.addFarmManager(managerId,farmId));
    }
    @PutMapping("/quit/{farmId}")
    public ResponseEntity<FarmResponse> quitFarmManager(
            @PathVariable Long farmId
    ){
        return ResponseEntity.ok(farmService.quitFarmManager(farmId));
    }
    @GetMapping
    public ResponseEntity<List<FarmResponse>> getAll(){
        List<FarmResponse> farmResponses = farmService.getAll();
        return ResponseEntity.ok(farmResponses);
    }
    @GetMapping("/{idFarm}")
    public  ResponseEntity<FarmResponse> getById(@PathVariable Long idFarm){
        FarmResponse farmResponse= farmService.getById(idFarm);
        return  ResponseEntity.ok(farmResponse);
    }
    @PutMapping("/{idFarm}")
    public ResponseEntity<FarmResponse> updated(@PathVariable Long idFarm, @RequestBody FarmRequest request){
        FarmResponse farmResponse=  farmService.update(request, idFarm);
        return ResponseEntity.ok(farmResponse);

    }
    @DeleteMapping("/{idFarm}")
    public ResponseEntity<FarmResponse> delete(@PathVariable Long idFarm){
        farmService.delete(idFarm);
       return ResponseEntity.noContent().build();
    }
    @GetMapping("/encargado/{id}")
    public ResponseEntity<List<FarmResponse>> getByFarmManager(@PathVariable Long id){
        return ResponseEntity.ok(farmService.getByFarmManager(id));
    }
    @GetMapping("/misgranjas")
    public ResponseEntity<List<FarmResponse>> getMyFarms(Authentication authentication){
        return ResponseEntity.ok(farmService.getMyFarms(authentication.getName()));
    }
}
