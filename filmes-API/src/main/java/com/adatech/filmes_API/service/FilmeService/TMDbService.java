package com.adatech.filmes_API.service.FilmeService;

import com.adatech.filmes_API.dto.response.FilmeResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TMDbService {

    private final RestTemplate restTemplate;
    private final String apiKey;
    private final String baseUrl;
    private final String format;

    public TMDbService(RestTemplate restTemplate,
                       @Value("${tmdb.api.key}") String apiKey,
                       @Value("${service.endereco.host}") String baseUrl,
                       @Value("${service.endereco.format}") String format) {
        this.restTemplate = restTemplate;
        this.apiKey = apiKey;
        this.baseUrl = baseUrl;
        this.format = format;
    }

    public FilmeResponseDTO execute(String movieId) {
        String url = String.format("%s/movie/%s?api_key=%s&format=%s", baseUrl, movieId, apiKey, format);
        return restTemplate.getForObject(url, FilmeResponseDTO.class);
    }

}