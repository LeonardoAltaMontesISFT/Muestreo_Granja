package com.blacmircrosystems.Peso_granja.mapper;

import com.blacmircrosystems.Peso_granja.dto.request.WeigthRecordRequest;
import com.blacmircrosystems.Peso_granja.dto.response.WeigthRecordResponse;
import com.blacmircrosystems.Peso_granja.entity.WeigthRecord;
import org.springframework.stereotype.Component;

@Component
public class WeigthRecordMapper {
    public WeigthRecord toEntity(WeigthRecordRequest request){
        WeigthRecord weigthRecord = new WeigthRecord();
        weigthRecord.setWeigth(request.getWeigth());
        return weigthRecord;
    }
    public WeigthRecordResponse toResponse(WeigthRecord weigthRecord){
        return  new WeigthRecordResponse(weigthRecord.getId(),weigthRecord.getBirdNumber(), weigthRecord.getWeigth());
    }
}
