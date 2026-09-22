package com.blacmircrosystems.Peso_granja.config;

import com.nimbusds.jose.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.*;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Configuration
public class JwtConfig {
    @Value("${app.jwt.secret}")
    private String secret;
    @Value("${app.jwt.issuer}")
    private String issuer;
    @Bean
    public SecretKey jwtSecretKey(){
        byte[] keyBytes = Base64.getDecoder().decode(secret);
        if (keyBytes.length < 32) {
            throw new IllegalStateException(
                    "La clave JWT para HS256 debe tener al menos 32 bytes"
            );
        }
      return   new SecretKeySpec(keyBytes, "HmacSHA256");
    }
    @Bean
    public JwtEncoder jwtEncoder(SecretKey jwtSecretKey){
        return NimbusJwtEncoder.withSecretKey(jwtSecretKey)
                .algorithm(MacAlgorithm.HS256).build();
    }
    @Bean
    public JwtDecoder jwtDecoder(SecretKey jwtSecretKey){
        NimbusJwtDecoder decoder = NimbusJwtDecoder.withSecretKey(jwtSecretKey)
                .macAlgorithm(MacAlgorithm.HS256)
                .build();
        decoder.setJwtValidator(
                JwtValidators.createDefaultWithIssuer(issuer)
        );
        return decoder;
    }
}
