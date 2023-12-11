package com.ecommerce.backend.Config;

import com.google.gson.Gson;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GSONConfig {
    @Bean
    public Gson gson() {
        return new Gson();
    }
}
