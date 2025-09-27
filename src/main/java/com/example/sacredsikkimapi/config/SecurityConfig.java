package com.example.sacredsikkimapi.config;

import com.example.sacredsikkimapi.security.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.cors.*;
import org.springframework.security.config.http.SessionCreationPolicy;

import java.util.List;
import java.util.Arrays;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserDetailsService userDetailsService;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOriginPatterns(Arrays.asList("*"));
        config.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE","OPTIONS","HEAD"));
        config.setAllowedHeaders(Arrays.asList("*"));
        config.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            // Critical for JWT APIs: ensures sessions are not created
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                // Allow Swagger and API Auth endpoints (REGISTRATION/LOGIN)
                .requestMatchers("/swagger-ui/**","/swagger-resources/**","/v3/api-docs/**","/webjars/**").permitAll()
                .requestMatchers("/api/auth/**").permitAll()
                
                // --- MODIFIED: PERMIT ALL API ENDPOINTS ---
                // Monastery CRUD
                .requestMatchers(HttpMethod.GET,"/api/monasteries/**").permitAll()
                .requestMatchers(HttpMethod.POST,"/api/monasteries").permitAll()
                .requestMatchers(HttpMethod.PUT,"/api/monasteries/**").permitAll()
                .requestMatchers(HttpMethod.DELETE,"/api/monasteries/**").permitAll()
                
                // Events (GET)
                .requestMatchers(HttpMethod.GET,"/api/events").permitAll()
                
                // Reviews
                .requestMatchers(HttpMethod.POST,"/api/reviews/**").permitAll()
                .requestMatchers("/api/reviews/all").permitAll()
                
                // Bookings (All methods)
                .requestMatchers("/api/bookings/**").permitAll() 
                
                // FALLBACK: Permit all requests that haven't been matched yet
                .anyRequest().permitAll()
            )
            .authenticationProvider(authenticationProvider())
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}