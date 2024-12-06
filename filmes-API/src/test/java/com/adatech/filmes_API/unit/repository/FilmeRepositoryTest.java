package com.adatech.filmes_API.unit.repository;

import com.adatech.filmes_API.model.Filme;
import com.adatech.filmes_API.repository.FilmeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class FilmeRepositoryTest {

    @Autowired
    private FilmeRepository filmeRepository;

    private Filme filme;

    @BeforeEach
    void setUp() {
        filme = new Filme();
        filme.setNomeFilme("John Wick");
        filme.setGenero("Ação");
        filme.setNota(8.5);
        filme.setComentario("Excelente filme");
        filme.setCorAvaliacao("Verde");
        filmeRepository.save(filme);
    }

    @Test
    void findByNomeFilmeContaining() {
        List<Filme> filmes = filmeRepository.findByNomeFilmeContaining("John");
        assertEquals(1, filmes.size());
        assertEquals("John Wick", filmes.get(0).getNomeFilme());
    }

    @Test
    void findByGeneroContaining() {
        List<Filme> filmes = filmeRepository.findByGeneroContaining("Ação");
        assertEquals(1, filmes.size());
        assertEquals("Ação", filmes.getFirst().getGenero());
    }

    @Test
    void findByNota() {
        List<Filme> filmes = filmeRepository.findByNota(8.5);
        assertEquals(1, filmes.size());
        assertEquals(8.5, filmes.getFirst().getNota());
    }

    @Test
    void findByCorAvaliacao() {
        List<Filme> filmes = filmeRepository.findByCorAvaliacao("Verde");
        assertEquals(1, filmes.size());
        assertEquals("Verde", filmes.getFirst().getCorAvaliacao());
    }
}