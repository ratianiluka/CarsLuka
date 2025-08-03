package com.carservice.cardemo.config;

import com.carservice.cardemo.config.JwtAuthenticationEntryPoint;
import com.carservice.cardemo.config.JwtTokenAuthenticationFilter;
import com.carservice.cardemo.config.JwtUsernameAndPasswordAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityCredentialsConfig {

    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final UserDetailsService userDetailsService;
    private final JwtConfig jwtConfig;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authManager) throws Exception {
        JwtUsernameAndPasswordAuthenticationFilter authFilter =
                new JwtUsernameAndPasswordAuthenticationFilter(authManager, jwtConfig);

        return http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(eh -> eh.authenticationEntryPoint(jwtAuthenticationEntryPoint))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, jwtConfig.getUri()).permitAll()
                        .requestMatchers(HttpMethod.GET,
                                "/swagger-ui.html/**",
                                "/v2/api-docs",
                                "/webjars/**",
                                "/swagger-resources/**",
                                "/configuration/**"
                        ).permitAll()

                        .requestMatchers(HttpMethod.POST, "/api/generateJWT/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/CommentController/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/PostController/posts/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/PostController/post/detail/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/RoleController/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/AppUserController/**").permitAll()

                        .requestMatchers("/api/PostController/**").hasRole("ADMIN")
                        .requestMatchers("/api/CategoryController/**").hasRole("ADMIN")
                        .requestMatchers("/api/CommentController/**").hasRole("ADMIN")

                        .anyRequest().authenticated()
                )
                .addFilter(authFilter)
                .addFilterAfter(new JwtTokenAuthenticationFilter(jwtConfig), UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
