package com.adatech.filmes_API.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .httpBasic(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req -> {
                    req.requestMatchers(HttpMethod.POST, "/usuarios")
                            .permitAll();
                    req.requestMatchers(HttpMethod.GET, "/usuarios/**")
                            .authenticated();

                    req.requestMatchers(new AntPathRequestMatcher("/h2/**"))
                            .permitAll();

                    try {
                        http.headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin));
                    } catch (Exception e) {
                        throw new RuntimeException("Frame options configuration not supported", e);
                    }
                    req.requestMatchers(new AntPathRequestMatcher("/favicon.ico"))
                            .permitAll();

                    req.requestMatchers(HttpMethod.GET, "/filmes/**").permitAll();
                    req.requestMatchers(HttpMethod.POST, "/filmes/**").permitAll();

                    req.requestMatchers(HttpMethod.GET, "/api/filmes/tmdb/**").permitAll();
                    req.requestMatchers(HttpMethod.POST, "/api/filmes/tmdb/**").permitAll();
                    req.requestMatchers(HttpMethod.GET, "/api/filmes/**").permitAll();

                }).build();
    }
}