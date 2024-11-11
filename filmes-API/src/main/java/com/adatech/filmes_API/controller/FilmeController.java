package com.adatech.filmes_API.controller;

import com.adatech.filmes_API.dto.request.CriarFilmeDTO;
import com.adatech.filmes_API.dto.response.FilmeResponseDTO;
import com.adatech.filmes_API.model.Filme;
import com.adatech.filmes_API.service.FilmeService.CriarFilmeService;
import com.adatech.filmes_API.service.FilmeService.ObterFilmePorFiltrosService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/filmes")
public class FilmeController {
    private final CriarFilmeService criarFilmeService;
    private final ObterFilmePorFiltrosService obterfilmePorFiltrosService;

    public FilmeController(CriarFilmeService criarFilmeService, ObterFilmePorFiltrosService obterfilmePorFiltrosService) {
        this.criarFilmeService = criarFilmeService;
        this.obterfilmePorFiltrosService = obterfilmePorFiltrosService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public FilmeResponseDTO criarFilme(@Valid @RequestBody CriarFilmeDTO filme) {
        return criarFilmeService.execute(filme);
    }

    @GetMapping("/{id}")
    public Filme obterFilmePorID(@PathVariable Long id) {
        return obterfilmePorFiltrosService.obterFilmePorId(id);
    }

    @GetMapping("/genero")
    public List<Filme> obterFilmesPorGenero(@RequestParam String genero) {
        return obterfilmePorFiltrosService.obterFilmesPorGenero(genero);
    }

    @GetMapping("/nomeFilme")
    public List<Filme> obterFilmesPorNomeFilme(@RequestParam String nome) {
        return obterfilmePorFiltrosService.obterFilmesPorNomeFilme(nome);
    }

    @GetMapping("/corAvaliacao")
    public List<Filme> obterFilmesPorCorAvaliacao(@RequestParam String corAvaliacao) {
        return obterfilmePorFiltrosService.obterFilmesPorCorAvaliacao(corAvaliacao);
    }

    @GetMapping("/nota")
    public List<Filme> obterFilmesPorNota(@RequestParam double nota) {
        return obterfilmePorFiltrosService.obterFilmesPorNota(nota);
    }
}