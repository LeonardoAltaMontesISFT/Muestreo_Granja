package com.blacmircrosystems.Peso_granja.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FarmManagerRequest {
    private String name;
    private String lastName;
    private Integer age;
    private String phone;
    private String email;
    private String userName;
    private String password;
    private Long idVeterinarian;
}
