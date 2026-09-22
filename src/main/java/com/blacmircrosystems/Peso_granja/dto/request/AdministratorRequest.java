package com.blacmircrosystems.Peso_granja.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdministratorRequest {
    @NotBlank(message = "El nombre es obligatorio")
    private String name;
    @NotBlank(message = "El apellido es obligatorio")
    private String lastName;
    @NotNull(message = "La edad es obligatoria")@Positive
    private Integer age;

    private String phone;
    @NotBlank@Email
    private String email;

    private String userName;
    private String password;
}
