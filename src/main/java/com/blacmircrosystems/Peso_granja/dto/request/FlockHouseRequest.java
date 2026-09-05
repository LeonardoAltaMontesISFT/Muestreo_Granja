package com.blacmircrosystems.Peso_granja.dto.request;

import com.blacmircrosystems.Peso_granja.entity.Flock;
import com.blacmircrosystems.Peso_granja.entity.PoultryHouse;
import com.blacmircrosystems.Peso_granja.enums.FlockHouseStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FlockHouseRequest {
 private Long flockId;
 private Long poultryHouseId;
private Integer initialMaleBirdCount;
private Integer initialFemaleBirdCount;
private FlockHouseStatus status;

}
