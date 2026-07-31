package com.blacmircrosystems.Peso_granja.service;

import com.blacmircrosystems.Peso_granja.dto.request.WeigthRecordRequest;
import com.blacmircrosystems.Peso_granja.dto.response.SamplingResponse;
import com.blacmircrosystems.Peso_granja.dto.response.WeigthRecordResponse;
import com.blacmircrosystems.Peso_granja.entity.Sampling;
import com.blacmircrosystems.Peso_granja.entity.WeigthRecord;
import com.blacmircrosystems.Peso_granja.enums.SamplingStatus;
import com.blacmircrosystems.Peso_granja.mapper.SamplingMapper;
import com.blacmircrosystems.Peso_granja.mapper.WeigthRecordMapper;
import com.blacmircrosystems.Peso_granja.repository.SamplingRepository;
import com.blacmircrosystems.Peso_granja.repository.WeigthRecordRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WeigthRecordService {
    private final WeigthRecordRepository weigthRecordRepository;
    private final SamplingRepository samplingRepository;
    private final SamplingMapper samplingMapper;
    private final WeigthRecordMapper weigthRecordMapper;

    private void recalculatedSampling(Sampling sampling ){
        List<WeigthRecord> records = weigthRecordRepository.findBySamplingIdOrderByBirdNumberAsc(sampling.getId());
        int amountBirds = records.size();
        double totalWeigth =  records.stream().mapToDouble(WeigthRecord::getWeigth).sum();
        double averageWeigth;
        if(amountBirds == 0){
             averageWeigth = 0.0;
        }else {
             averageWeigth= totalWeigth/amountBirds;
        }
        double minimumWeigth= records.stream().mapToDouble(WeigthRecord::getWeigth).min().orElse(0.0);
        double maximumWeigth = records.stream().mapToDouble(WeigthRecord ::getWeigth).max().orElse(0.0);
        sampling.setMinimumWeight(minimumWeigth);
        sampling.setMaximumWeight(maximumWeigth);
        sampling.setTotalWeight(totalWeigth);
        sampling.setAverageWeight(averageWeigth);
        sampling.setAmountBirds(amountBirds);
    }
    @Transactional
    public SamplingResponse addWeigth(Long idSampling, WeigthRecordRequest weigthRecord){
        Sampling sampling = samplingRepository.findById(idSampling).orElseThrow(()-> new RuntimeException("Muestreo no encontrado con este id : " + idSampling));

        if(sampling.getSamplingStatus()== SamplingStatus.COMPLETED){
            throw new RuntimeException("No se puede agregar peso a un muestreo ya terminado ");
        }
        if(weigthRecord.getWeigth() <=0){
            throw new RuntimeException("El peso debe ser mayor a 0");

        }
        System.out.println(sampling.getAmountBirds());
        int nextBirdNumber = sampling.getAmountBirds()+1;
        WeigthRecord weigthRecord1= new WeigthRecord();
        weigthRecord1.setSampling(sampling);
        weigthRecord1.setBirdNumber(nextBirdNumber);
        weigthRecord1.setWeigth(weigthRecord.getWeigth());

        weigthRecordRepository.save(weigthRecord1);
        recalculatedSampling(sampling);
        Sampling save = samplingRepository.save(sampling);
        return samplingMapper.toResponse(save);

    }

    @Transactional
    public SamplingResponse addWeigths(Long idSampling, List<WeigthRecordRequest> requestList){
        Sampling sampling = samplingRepository.findById(idSampling).orElseThrow(()-> new RuntimeException("Muestreo no encontrado con este id: " + idSampling));
        if(sampling.getSamplingStatus()== SamplingStatus.COMPLETED){
            throw new RuntimeException("No se puede agregar un muestreo ya terminado");
        }

        for(int i = 0;i< requestList.size();i++){
            int nextBirdNumber = sampling.getAmountBirds()+1;
            WeigthRecord weigthRecord1= new WeigthRecord();
            weigthRecord1.setSampling(sampling);
            weigthRecord1.setBirdNumber(nextBirdNumber);
            weigthRecord1.setWeigth(requestList.get(i).getWeigth());

            weigthRecordRepository.save(weigthRecord1);
            recalculatedSampling(sampling);

        }
        Sampling save = samplingRepository.save(sampling);

        return  samplingMapper.toResponse(save);

    }
    public List<WeigthRecordResponse> getAll(Long id){
        return weigthRecordRepository.findBySamplingId(id).stream().map(weigthRecordMapper::toResponse).toList();
    }
}
