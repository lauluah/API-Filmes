package com.adatech.filmes_API.external.client;

import com.adatech.filmes_API.dto.response.FilmeApiResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class FilmeApiCliente {

    private final RestTemplate restTemplate;
    private final String apiKey;
    private final String baseUrl;

    public FilmeApiCliente(RestTemplate restTemplate,
                           @Value("${tmdb.api.key}") String apiKey,
                           @Value("${service.endereco.host}") String baseUrl) {
        this.restTemplate = restTemplate;
        this.apiKey = apiKey;
        this.baseUrl = baseUrl;
    }

    public FilmeApiResponseDTO obterDetalhesFilme(String movieName) {
        String url = String.format("%s/search/movie?query=%s&api_key=%s", baseUrl, movieName, apiKey);
        return restTemplate.getForObject(url, FilmeApiResponseDTO.class);
    }

    public FilmeApiResponseDTO[] obterFilmesPorGenero(String genre) {
        String url = String.format("%s/discover/movie?with_genres=%s&api_key=%s", baseUrl, genre, apiKey);
        return restTemplate.getForObject(url, FilmeApiResponseDTO[].class);
    }
}