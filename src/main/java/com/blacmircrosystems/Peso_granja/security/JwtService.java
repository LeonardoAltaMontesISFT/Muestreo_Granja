package com.blacmircrosystems.Peso_granja.security;

import lombok.RequiredArgsConstructor;
import org.springframework.http.support.JettyHeadersAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JwtService {
    private final JwtEncoder jwtEncoder;
    @Value("${app.jwt.issuer}")
    private String issuer;
    @Value("${app.jwt.expiration-seconds}")
    private long expirationSeconds;
    public String generateToken(Authentication authentication){
        Instant now = Instant.now();
        Instant expiresAt =now.plusSeconds(expirationSeconds);
        List<String> roles = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .toList();
        JwtClaimsSet claims= JwtClaimsSet.builder()
                .issuer(issuer)
                .subject(authentication.getName())
                .issuedAt(now)
                .expiresAt(expiresAt)
                .claim("roles",roles)
                .build();
        JwsHeader header = JwsHeader.with(MacAlgorithm.HS256).build();
        return jwtEncoder.encode(
                JwtEncoderParameters.from(header, claims)
        ).getTokenValue();
    }



}
