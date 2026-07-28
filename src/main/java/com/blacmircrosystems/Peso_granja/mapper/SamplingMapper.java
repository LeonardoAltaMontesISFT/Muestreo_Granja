package com.blacmircrosystems.Peso_granja.mapper;

import com.blacmircrosystems.Peso_granja.dto.request.SamplingRequest;
import com.blacmircrosystems.Peso_granja.dto.response.SamplingResponse;
import com.blacmircrosystems.Peso_granja.entity.Sampling;
import com.blacmircrosystems.Peso_granja.enums.SamplingStatus;
import org.springframework.stereotype.Component;

@Component
public class SamplingMapper {
    public Sampling toEntity(SamplingRequest request){
        Sampling sampling = new Sampling();
        sampling.setAgeBirds(request.getAgeBirds());
        sampling.setSex(request.getSex());
        sampling.setZone(request.getZone());
        sampling.setSamplingStatus(SamplingStatus.OPEN);
        sampling.setAmountBirds(0);
        sampling.setAverageWeight(0.0);
        sampling.setTotalWeight(0.0);
        sampling.setMaximumWeight(0.0);
        sampling.setMinimumWeight(0.0);

        return sampling;
    }

    public SamplingResponse toResponse(Sampling sampling) {
        return new SamplingResponse(
                sampling.getId(),
                sampling.getPoultryHouse().getId(),
                sampling.getPoultryHouse().getNumberPoultry(),
                sampling.getAgeBirds(),
                sampling.getSex(),
                sampling.getZone(),
                sampling.getSamplingStatus(),
                sampling.getAmountBirds(),
                sampling.getTotalWeight(),
                sampling.getAverageWeight(),
                sampling.getMinimumWeight(),
                sampling.getMaximumWeight(),
                sampling.getCreatedDate()
        );
    }
}
