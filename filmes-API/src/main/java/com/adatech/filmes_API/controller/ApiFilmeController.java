package com.adatech.filmes_API.controller;

import com.adatech.filmes_API.dto.response.ApiFilmeResponseDTO;
import com.adatech.filmes_API.model.ApiFilme;
import com.adatech.filmes_API.service.ApiFilme.TMDbService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/filmes")
@CrossOrigin(origins = "http://localhost:63342")
public class ApiFilmeController {
    private final TMDbService tmdbService;

    public ApiFilmeController(TMDbService tmdbService) {
        this.tmdbService = tmdbService;
    }

    @GetMapping("/tmdb/nome/{title}")
    public ResponseEntity<ApiFilmeResponseDTO> obterDetalhesFilmeTMDb(@PathVariable String title) {
        ApiFilmeResponseDTO filmeResponseDTO = tmdbService.obterDetalhesFilme(title);
        return ResponseEntity.status(HttpStatus.OK).body(filmeResponseDTO);
    }

    @PostMapping("/tmdb/nome/{title}")
    public ResponseEntity<ApiFilme> salvarDetalhesFilmeTMDb(@PathVariable String title) {
        ApiFilme apiFilme = tmdbService.salvarDetalhesFilme(title);
        if (apiFilme != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(apiFilme);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}