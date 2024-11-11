package com.adatech.filmes_API.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Configura as permissões de segurança
        http
                .cors(customizer -> customizer.configurationSource(corsConfigurationSource())) // Configuração CORS
                .csrf().disable() // Desabilita CSRF
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers(HttpMethod.POST, "/usuarios").permitAll()
                                .requestMatchers(HttpMethod.GET, "/usuarios/**").authenticated()
                                .requestMatchers(HttpMethod.GET, "/filmes/**").permitAll()
                                .requestMatchers(HttpMethod.POST, "/filmes/**").permitAll()
                                .requestMatchers(HttpMethod.GET, "/api/filmes/tmdb/**").permitAll()
                                .requestMatchers(HttpMethod.POST, "/api/filmes/tmdb/**").permitAll()
                                .requestMatchers(HttpMethod.GET, "/api/filmes/**").permitAll()
                                // Permitir acesso ao H2 Console (importante para o seu caso)
                                .requestMatchers("/h2-console/**").permitAll()
                )
                // Configurações específicas para o H2 Console
                .headers(headers -> headers.frameOptions().sameOrigin());

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:63342"));  // Permite a origem do seu frontend
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));  // Permite os métodos
        configuration.setAllowedHeaders(List.of("*"));  // Permite todos os cabeçalhos
        configuration.setAllowCredentials(true);  // Permite o envio de credenciais (como cookies)

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);  // Aplica CORS para todas as rotas
        return source;
    }
}
