package com.blacmircrosystems.Peso_granja.service;

import com.blacmircrosystems.Peso_granja.dto.request.LoginRequest;
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
    public String login(LoginRequest request){
        Authentication authentication = authenticate(request);
        return jwtService.generateToken(authentication);
    }
}
