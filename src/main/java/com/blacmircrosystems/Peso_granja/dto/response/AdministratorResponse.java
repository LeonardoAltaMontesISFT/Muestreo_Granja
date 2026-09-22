package com.blacmircrosystems.Peso_granja.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdministratorResponse {
    private Long id;
    private String name;
    private String lastName;
    private Integer age;
    private String phone;
    private String role;
    private String username;
}
