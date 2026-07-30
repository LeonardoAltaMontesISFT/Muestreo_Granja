package com.blacmircrosystems.Peso_granja.mapper;

import com.blacmircrosystems.Peso_granja.dto.request.PoultryHouseRequest;
import com.blacmircrosystems.Peso_granja.dto.response.PoultryHouseResponse;
import com.blacmircrosystems.Peso_granja.entity.PoultryHouse;
import org.springframework.stereotype.Component;

@Component

public class PoultryHouseMapper {
    public PoultryHouse toEntity(PoultryHouseRequest request){
        PoultryHouse poultryHouse= new PoultryHouse();
        poultryHouse.setNumberPoultry(request.getNumberPoultry());
        return poultryHouse;
    }
    public PoultryHouseResponse toResponse(PoultryHouse poultryHouse){
        return new PoultryHouseResponse(poultryHouse.getId(),
                poultryHouse.getNumberPoultry(),
                poultryHouse.getFarm().getId(),
                poultryHouse.getFarm().getName()
        );

    }
}
