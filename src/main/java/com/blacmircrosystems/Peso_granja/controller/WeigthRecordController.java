package com.blacmircrosystems.Peso_granja.controller;

import com.blacmircrosystems.Peso_granja.dto.request.WeigthRecordRequest;
import com.blacmircrosystems.Peso_granja.dto.response.SamplingResponse;
import com.blacmircrosystems.Peso_granja.dto.response.WeigthRecordResponse;
import com.blacmircrosystems.Peso_granja.entity.Sampling;
import com.blacmircrosystems.Peso_granja.entity.WeigthRecord;
import com.blacmircrosystems.Peso_granja.service.WeigthRecordService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/muestreo")
public class WeigthRecordController {
    private final WeigthRecordService weigthRecordService;
    @PostMapping("/{idSampling}")
    public ResponseEntity<SamplingResponse> addWeigth(@PathVariable Long idSampling, @RequestBody WeigthRecordRequest recordRequest){
        SamplingResponse samplingResponse = weigthRecordService.addWeigth(idSampling,recordRequest);
       return ResponseEntity.status(HttpStatus.CREATED).body(samplingResponse);
    }
    @GetMapping("todos/{idSampling}")
    public ResponseEntity<List<WeigthRecordResponse>> getAll(@PathVariable Long idSampling){
        List<WeigthRecordResponse> recordResponses = weigthRecordService.getAll(idSampling);
        return ResponseEntity.ok(recordResponses);
    }
    @PostMapping("lista/{idSampling}")
    public  ResponseEntity<SamplingResponse> addWeigts(@PathVariable Long idSampling,@RequestBody List<WeigthRecordRequest> requestList){
        SamplingResponse samplingResponse =  weigthRecordService.addWeights(idSampling,requestList);
        return  ResponseEntity.status(HttpStatus.CREATED).body(samplingResponse);
    }

}
