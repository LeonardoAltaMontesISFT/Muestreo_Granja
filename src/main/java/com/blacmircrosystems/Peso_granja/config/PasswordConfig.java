package com.blacmircrosystems.Peso_granja.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class PasswordConfig {
    @Bean
    public PasswordEncoder passwordEncoder (){
        // BCrypt convierte "password123" → "$2a$10$xyz..."
        // .encode()  → encripta al guardar
        // .matches() → compara al hacer login sin necesidad de desencriptar
return  new BCryptPasswordEncoder();
    }
}
