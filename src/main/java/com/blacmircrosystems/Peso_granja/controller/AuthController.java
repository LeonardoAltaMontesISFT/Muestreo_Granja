package com.blacmircrosystems.Peso_granja.controller;

import com.blacmircrosystems.Peso_granja.dto.request.LoginRequest;
import com.blacmircrosystems.Peso_granja.dto.response.LoginResponse;
import com.blacmircrosystems.Peso_granja.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse>login(@RequestBody LoginRequest request){
        String accessToken = authService.login(request);
        LoginResponse response= new LoginResponse(accessToken,"Bearer");
        return ResponseEntity.ok(response);
    }
}
