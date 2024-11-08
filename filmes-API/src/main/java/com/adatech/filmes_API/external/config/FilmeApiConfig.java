package com.adatech.filmes_API.external.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class FilmeApiConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}