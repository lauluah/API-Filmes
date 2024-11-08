package com.adatech.filmes_API.controller;

import com.adatech.filmes_API.dto.request.CriarFilmeDTO;
import com.adatech.filmes_API.dto.response.FilmeApiResponseDTO;
import com.adatech.filmes_API.dto.response.FilmeResponseDTO;
import com.adatech.filmes_API.service.FilmeService.CriarFilmeService;
import com.adatech.filmes_API.service.FilmeService.ObterFilmePorIDService;
import com.adatech.filmes_API.service.FilmeService.TMDbService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/filmes")
public class FilmeController {
    private final CriarFilmeService criarFilmeService;
    private final ObterFilmePorIDService obterFilmePorIDService;
    private final TMDbService tmdbService;

    public FilmeController(CriarFilmeService criarFilmeService, ObterFilmePorIDService obterFilmePorIDService,
                           TMDbService tmdbService) {
        this.criarFilmeService = criarFilmeService;
        this.obterFilmePorIDService = obterFilmePorIDService;
        this.tmdbService = tmdbService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public FilmeResponseDTO criarFilme(@Valid @RequestBody CriarFilmeDTO filme) {
        return criarFilmeService.execute(filme);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> obterFilmePorID(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(obterFilmePorIDService.execute(id));
    }

    @GetMapping("/tmdb/nome/{nome}")
    public ResponseEntity<FilmeApiResponseDTO> obterDetalhesFilmeTMDb(@PathVariable String nome) {
        FilmeApiResponseDTO filmeResponseDTO = tmdbService.obterDetalhesFilme(nome);
        return ResponseEntity.status(HttpStatus.OK).body(filmeResponseDTO);
    }

    @GetMapping("/tmdb/genero/{genero}")
    public ResponseEntity<FilmeApiResponseDTO[]> obterFilmesPorGenero(@PathVariable String genero) {
        FilmeApiResponseDTO[] filmes = tmdbService.obterFilmesPorGenero(genero);
        return ResponseEntity.status(HttpStatus.OK).body(filmes);
    }

}
