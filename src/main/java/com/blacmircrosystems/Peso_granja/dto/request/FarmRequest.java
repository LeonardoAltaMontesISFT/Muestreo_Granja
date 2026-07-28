package com.blacmircrosystems.Peso_granja.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FarmRequest {
    private String name;
    private String ubicacion;
}
