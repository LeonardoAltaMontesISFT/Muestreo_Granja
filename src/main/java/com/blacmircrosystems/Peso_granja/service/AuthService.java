package com.blacmircrosystems.Peso_granja.service;

import com.blacmircrosystems.Peso_granja.dto.request.LoginRequest;
import com.blacmircrosystems.Peso_granja.dto.response.LoginResponse;
import com.blacmircrosystems.Peso_granja.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    public Authentication authenticate(LoginRequest request) {
        Authentication credentials =
                UsernamePasswordAuthenticationToken.unauthenticated(
                        request.getUsername(),
                        request.getPassword()
                );

        return authenticationManager.authenticate(credentials);
    }
    public LoginResponse login(LoginRequest request) {
        Authentication authentication = authenticate(request);
        String accessToken = jwtService.generateToken(authentication);

        String role = authentication.getAuthorities().stream()
                .map(authority -> authority.getAuthority())
                .filter(authority -> authority.startsWith("ROLE_"))
                .map(authority -> authority.substring("ROLE_".length()))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalStateException("La cuenta no tiene un rol asignado")
                );

        return new LoginResponse(accessToken, "Bearer", role);
    }
}
