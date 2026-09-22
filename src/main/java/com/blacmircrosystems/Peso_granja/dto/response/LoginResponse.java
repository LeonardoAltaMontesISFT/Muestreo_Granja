package com.blacmircrosystems.Peso_granja.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class LoginResponse {
    private String accessToken;
    private String tokenType;
}
