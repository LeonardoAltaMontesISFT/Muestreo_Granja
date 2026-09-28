package com.blacmircrosystems.Peso_granja.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public AuthenticationManager authenticationManager(
            UserDetailsService userDetailsService,
            PasswordEncoder passwordEncoder
    ) {
        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider(userDetailsService);

        provider.setPasswordEncoder(passwordEncoder);

        return new ProviderManager(provider);
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter authoritiesConverter =
                new JwtGrantedAuthoritiesConverter();

        authoritiesConverter.setAuthoritiesClaimName("roles");
        authoritiesConverter.setAuthorityPrefix("");

        JwtAuthenticationConverter converter =
                new JwtAuthenticationConverter();

        converter.setJwtGrantedAuthoritiesConverter(authoritiesConverter);

        return converter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http,
            JwtAuthenticationConverter jwtAuthenticationConverter
    ) throws Exception {

        http
                .csrf(csrf -> csrf.disable())

                .sessionManagement(session ->
                        session.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS
                        )
                )

                .authorizeHttpRequests(auth -> auth

                        // =========================
                        // PÚBLICO
                        // =========================
                        .requestMatchers(
                                HttpMethod.POST,
                                "/api/auth/login"
                        ).permitAll()


                        // =========================
                        // ADMIN
                        // =========================
                        .requestMatchers("/api/admin/**")
                        .hasRole("ADMIN")

                        // Crear recursos administrativos
                        .requestMatchers(
                                HttpMethod.POST,
                                "/api/granja",
                                "/api/casetas",
                                "/api/encargado",
                                "/api/veterinario"
                        ).hasRole("ADMIN")

                        // Modificar recursos administrativos
                        .requestMatchers(
                                HttpMethod.PUT,
                                "/api/granja/**",
                                "/api/casetas/**"
                        ).hasRole("ADMIN")

                        // Eliminar recursos administrativos
                        .requestMatchers(
                                HttpMethod.DELETE,
                                "/api/granja/**",
                                "/api/casetas/**",
                                "/api/encargado/**",
                                "/api/veterinario/**"
                        ).hasRole("ADMIN")


                        // =========================
                        // FARM MANAGER
                        // =========================

                        // Consultar SU información
                        .requestMatchers(
                                HttpMethod.GET,
                                "/api/encargado/me"
                        ).hasRole("FARM_MANAGER")

                        // Consultar SUS granjas
                        .requestMatchers(
                                HttpMethod.GET,
                                "/api/granja/misgranjas/**"
                        ).hasRole("FARM_MANAGER")

                        // Consultar casetas
                        .requestMatchers(
                                HttpMethod.GET,
                                "/api/casetas/**"
                        ).hasRole("FARM_MANAGER")

                        // Parvadas
                        .requestMatchers(
                                "/api/parvada/**"
                        ).hasRole("FARM_MANAGER")

                        // Relación parvada-caseta
                        .requestMatchers(
                                "/api/parvadacaseta/**"
                        ).hasRole("FARM_MANAGER")

                        // Mortalidad
                        .requestMatchers(
                                "/api/mortalidad/**"
                        ).hasRole("FARM_MANAGER")

                        // Muestreos
                        .requestMatchers(
                                "/api/muestreos/**",
                                "/api/muestreo/**"
                        ).hasRole("FARM_MANAGER")


                        // =========================
                        // CONSULTAS ADMIN
                        // IMPORTANTE: después de /me
                        // =========================
                        .requestMatchers(
                                HttpMethod.GET,
                                "/api/encargado/**",
                                "/api/veterinario/**"
                        ).hasRole("ADMIN")


                        // Cualquier otra ruta requiere autenticación
                        .anyRequest().authenticated()

                )

                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt ->
                                jwt.jwtAuthenticationConverter(
                                        jwtAuthenticationConverter
                                )
                        )
                )

                .httpBasic(basic -> basic.disable())
                .formLogin(form -> form.disable());

        return http.build();
    }
}