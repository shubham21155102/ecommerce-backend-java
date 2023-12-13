package com.ecommerce.backend.configruation;

import com.ecommerce.backend.security.JwtAuthenticationEntryPoint;
import com.ecommerce.backend.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {


    @Autowired
    private JwtAuthenticationEntryPoint point;
    @Autowired
    private JwtAuthenticationFilter filter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
         http.csrf(csrf->csrf.disable())
                 .cors(cors->cors.disable())
                    .authorizeRequests(
                            auth->  auth.requestMatchers("/auth/login").permitAll()
                                    .requestMatchers("/login").permitAll()
                                    .requestMatchers("/register").permitAll()
                                    .requestMatchers("/users").hasRole("ADMIN")
                                    .requestMatchers("/current-user").authenticated()
                                    .requestMatchers("/users/**").permitAll()
                                            .anyRequest().authenticated());
            http.exceptionHandling(ex->ex.authenticationEntryPoint(point));
            http.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
            http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
                    return http.build();


    }


}
