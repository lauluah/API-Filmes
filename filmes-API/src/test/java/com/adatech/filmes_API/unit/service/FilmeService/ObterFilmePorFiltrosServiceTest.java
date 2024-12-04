package com.adatech.filmes_API.unit.service.FilmeService;

import com.adatech.filmes_API.exception.FilmeNaoEncontradoException;
import com.adatech.filmes_API.model.Filme;
import com.adatech.filmes_API.repository.FilmeRepository;
import com.adatech.filmes_API.service.FilmeService.ObterFilmePorFiltrosService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ObterFilmePorFiltrosServiceTest {

    @InjectMocks
    ObterFilmePorFiltrosService obterFilmePorFiltrosService;

    @Mock
    FilmeRepository filmeRepository;

    Filme filme;

    @BeforeEach
    void setUp() {
        filme = new Filme(1L, "John Wick", "Ação", 8.5, "Excelente filme", "Verde");
    }

    @Test
    void obterFilmesPorGenero_generoExistente_retornaFilmes() {
        when(filmeRepository.findByGeneroContaining("Ação")).thenReturn(List.of(filme));

        List<Filme> filmes = obterFilmePorFiltrosService.obterFilmesPorGenero("Ação");

        assertEquals(1, filmes.size());
        assertEquals(filme, filmes.getFirst());
    }

    @Test
    void obterFilmesPorGenero_generoNaoExistente_lancaExcecao() {
        when(filmeRepository.findByGeneroContaining("Comédia")).thenReturn(Collections.emptyList());

        assertThrows(FilmeNaoEncontradoException.class, () -> obterFilmePorFiltrosService.obterFilmesPorGenero("Comédia"));
    }

    @Test
    void obterFilmesPorNomeFilme_nomeExistente_retornaFilmes() {
        when(filmeRepository.findByNomeFilmeContaining("John Wick")).thenReturn(List.of(filme));

        List<Filme> filmes = obterFilmePorFiltrosService.obterFilmesPorNomeFilme("John Wick");

        assertEquals(1, filmes.size());
        assertEquals(filme, filmes.getFirst());
    }

    @Test
    void obterFilmesPorNomeFilme_nomeNaoExistente_lancaExcecao() {
        when(filmeRepository.findByNomeFilmeContaining("Oppenheimer")).thenReturn(Collections.emptyList());

        assertThrows(FilmeNaoEncontradoException.class, () -> obterFilmePorFiltrosService.obterFilmesPorNomeFilme("Oppenheimer"));
    }

    @Test
    void obterFilmesPorNota_notaExistente_retornaFilmes() {
        when(filmeRepository.findByNota(8.5)).thenReturn(List.of(filme));

        List<Filme> filmes = obterFilmePorFiltrosService.obterFilmesPorNota(8.5);

        assertEquals(1, filmes.size());
        assertEquals(filme, filmes.getFirst());
    }

    @Test
    void obterFilmesPorNota_notaNaoExistente_lancaExcecao() {
        when(filmeRepository.findByNota(6.0)).thenReturn(Collections.emptyList());

        assertThrows(FilmeNaoEncontradoException.class, () -> obterFilmePorFiltrosService.obterFilmesPorNota(6.0));
    }

    @Test
    void obterFilmesPorCorAvaliacao_corExistente_retornaFilmes() {
        when(filmeRepository.findByCorAvaliacao("Verde")).thenReturn(List.of(filme));

        List<Filme> filmes = obterFilmePorFiltrosService.obterFilmesPorCorAvaliacao("Verde");

        assertEquals(1, filmes.size());
        assertEquals(filme, filmes.getFirst());
    }

    @Test
    void obterFilmesPorCorAvaliacao_corNaoExistente_lancaExcecao() {
        when(filmeRepository.findByCorAvaliacao("Azul")).thenReturn(Collections.emptyList());

        assertThrows(FilmeNaoEncontradoException.class, () -> obterFilmePorFiltrosService.obterFilmesPorCorAvaliacao("Azul"));
    }

    @Test
    void obterFilmePorId_idExistente_retornaFilme() {
        when(filmeRepository.findById(1L)).thenReturn(Optional.of(filme));

        Filme filmeEncontrado = obterFilmePorFiltrosService.obterFilmePorId(1L);

        assertEquals(filme, filmeEncontrado);
    }

    @Test
    void obterFilmePorId_idNaoExistente_lancaExcecao() {
        when(filmeRepository.findById(2L)).thenReturn(Optional.empty());

        assertThrows(FilmeNaoEncontradoException.class, () -> obterFilmePorFiltrosService.obterFilmePorId(2L));
    }
}