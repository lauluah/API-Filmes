package com.adatech.filmes_API.unit.controller;

import com.adatech.filmes_API.controller.FilmeController;
import com.adatech.filmes_API.dto.request.CriarFilmeDTO;
import com.adatech.filmes_API.dto.response.FilmeResponseDTO;
import com.adatech.filmes_API.model.Filme;
import com.adatech.filmes_API.service.FilmeService.CriarFilmeService;
import com.adatech.filmes_API.service.FilmeService.DeletarFilmeService;
import com.adatech.filmes_API.service.FilmeService.ObterFilmePorFiltrosService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FilmeControllerUnitTest {

    @Mock
    private CriarFilmeService criarFilmeService;

    @Mock
    private ObterFilmePorFiltrosService obterFilmePorFiltrosService;

    @Mock
    private DeletarFilmeService deletarFilmeService;

    @InjectMocks
    private FilmeController filmeController;

    private Filme filme;
    private CriarFilmeDTO criarFilmeDTO;
    private FilmeResponseDTO filmeResponseDTO;

    @BeforeEach
    void setUp() {
        filme = new Filme(1L, "John Wick", "Ação", 8.5, "Excelente filme", "Verde");
        criarFilmeDTO = new CriarFilmeDTO("John Wick", "Ação", 8.5, "Excelente filme", "Verde");
        filmeResponseDTO = new FilmeResponseDTO("John Wick", "Ação", 8.5, "Excelente filme", "Verde");
    }

    @Test
    void criarFilme() {
        when(criarFilmeService.execute(any(CriarFilmeDTO.class))).thenReturn(filmeResponseDTO);

        FilmeResponseDTO response = filmeController.criarFilme(criarFilmeDTO);

        assertEquals(filmeResponseDTO.getNomeFilme(), response.getNomeFilme());
        assertEquals(filmeResponseDTO.getGenero(), response.getGenero());
        assertEquals(filmeResponseDTO.getNota(), response.getNota());
        assertEquals(filmeResponseDTO.getComentario(), response.getComentario());
        assertEquals(filmeResponseDTO.getCorAvaliacao(), response.getCorAvaliacao());
    }

    @Test
    void obterFilmePorID() {
        when(obterFilmePorFiltrosService.obterFilmePorId(1L)).thenReturn(filme);

        Filme response = filmeController.obterFilmePorID(1L);

        assertEquals(filme.getId(), response.getId());
        assertEquals(filme.getNomeFilme(), response.getNomeFilme());
        assertEquals(filme.getGenero(), response.getGenero());
        assertEquals(filme.getNota(), response.getNota());
        assertEquals(filme.getComentario(), response.getComentario());
        assertEquals(filme.getCorAvaliacao(), response.getCorAvaliacao());
    }

    @Test
    void obterFilmesPorGenero() {
        when(obterFilmePorFiltrosService.obterFilmesPorGenero("Ação")).thenReturn(List.of(filme));

        List<Filme> response = filmeController.obterFilmesPorGenero("Ação");

        assertEquals(1, response.size());
        assertEquals(filme.getNomeFilme(), response.getFirst().getNomeFilme());
    }

    @Test
    void obterFilmesPorNome() {
        when(obterFilmePorFiltrosService.obterFilmesPorNomeFilme("John Wick")).thenReturn(List.of(filme));

        List<Filme> response = filmeController.obterFilmesPorNomeFilme("John Wick");

        assertEquals(1, response.size());
        assertEquals(filme.getNomeFilme(), response.getFirst().getNomeFilme());
    }

    @Test
    void obterFilmesPorCorAvaliacao() {
        when(obterFilmePorFiltrosService.obterFilmesPorCorAvaliacao("Verde")).thenReturn(List.of(filme));

        List<Filme> response = filmeController.obterFilmesPorCorAvaliacao("Verde");

        assertEquals(1, response.size());
        assertEquals(filme.getCorAvaliacao(), response.getFirst().getCorAvaliacao());
    }

    @Test
    void obterFilmesPorNota() {
        when(obterFilmePorFiltrosService.obterFilmesPorNota(8.5)).thenReturn(List.of(filme));

        List<Filme> response = filmeController.obterFilmesPorNota(8.5);

        assertEquals(1, response.size());
        assertEquals(filme.getNota(), response.getFirst().getNota());
    }

    @Test
    void deletarFilme() {
        when(deletarFilmeService.deletarFilme(1L)).thenReturn(ResponseEntity.ok("Filme deletado com sucesso."));

        ResponseEntity<String> response = filmeController.deletarFilme(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Filme deletado com sucesso.", response.getBody());
    }
}