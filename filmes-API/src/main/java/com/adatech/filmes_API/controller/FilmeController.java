package com.adatech.filmes_API.controller;

import com.adatech.filmes_API.dto.request.CriarFilmeDTO;
import com.adatech.filmes_API.dto.response.FilmeResponseDTO;
import com.adatech.filmes_API.service.FilmeService.CriarFilmeService;
import com.adatech.filmes_API.service.FilmeService.ObterFilmePorIDService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/filmes")
public class FilmeController {
    private final CriarFilmeService criarFilmeService;
    private final ObterFilmePorIDService obterFilmePorIDService;

    public FilmeController(CriarFilmeService criarFilmeService, ObterFilmePorIDService obterFilmePorIDService) {
        this.criarFilmeService = criarFilmeService;
        this.obterFilmePorIDService = obterFilmePorIDService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public FilmeResponseDTO criarFilme(@Valid @RequestBody CriarFilmeDTO filme) {
        return criarFilmeService.execute(filme);
    }

    //P
    @GetMapping("/{id}")
    public ResponseEntity<Object> obterFilmePorID(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(obterFilmePorIDService.execute(id));
    }
}
