package com.adatech.filmes_API.controller;

import com.adatech.filmes_API.dto.response.ApiFilmeResponseDTO;
import com.adatech.filmes_API.service.ApiFilme.TMDbService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/filmes")
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

//    @GetMapping("/tmdb/nome/{title}")
//    public ResponseEntity<ApiFilmeResponseDTO> obterDetalhesFilmeTMDb(@PathVariable String title) {
//        ApiFilmeResponseDTO filmeResponseDTO = tmdbService.obterDetalhesFilme(title);
//        return ResponseEntity.status(HttpStatus.OK).body(filmeResponseDTO);
//    }
//
//    @GetMapping("/tmdb/genero/{genres}")
//    public ResponseEntity<ApiFilmeResponseDTO[]> obterFilmesPorGenero(@PathVariable String genres) {
//        ApiFilmeResponseDTO[] filmes = tmdbService.obterFilmesPorGenero(genres);
//        return ResponseEntity.status(HttpStatus.OK).body(filmes);
//    }

}