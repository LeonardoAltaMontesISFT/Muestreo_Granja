package com.blacmircrosystems.Peso_granja.dto.response;

import com.blacmircrosystems.Peso_granja.entity.Farm;
import com.blacmircrosystems.Peso_granja.enums.FlockStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FlockResponse {
private Long id;
private String code;
private FlockStatus status;
private String nameFarm;
private LocalDate startTime;
private LocalDate closed;

}
