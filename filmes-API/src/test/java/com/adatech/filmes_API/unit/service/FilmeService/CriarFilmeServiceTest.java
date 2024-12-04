package com.adatech.filmes_API.unit.service.FilmeService;

import com.adatech.filmes_API.dto.request.CriarFilmeDTO;
import com.adatech.filmes_API.dto.response.FilmeResponseDTO;
import com.adatech.filmes_API.model.Filme;
import com.adatech.filmes_API.repository.FilmeRepository;
import com.adatech.filmes_API.service.FilmeService.CriarFilmeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CriarFilmeServiceTest {

    @InjectMocks
    CriarFilmeService criarFilmeService;

    @Mock
    FilmeRepository filmeRepository;

    Filme filmeEntity;

    @BeforeEach
    public void setUp() {
        filmeEntity = new Filme(1L, "John Wick", "Ação", 8.5, "Excelente filme", "Verde");
    }

    @Test
    void dadosValidosFilme_criarFilme_retornaFilmeCriado() {
        CriarFilmeDTO criarFilmeDTO = new CriarFilmeDTO();
        criarFilmeDTO.setNomeFilme("John Wick");
        criarFilmeDTO.setGenero("Ação");
        criarFilmeDTO.setNota(8.5);
        criarFilmeDTO.setComentario("Excelente filme");
        criarFilmeDTO.setCorAvaliacao("Verde");

        when(filmeRepository.save(any(Filme.class))).thenReturn(filmeEntity);

        FilmeResponseDTO response = criarFilmeService.execute(criarFilmeDTO);

        assertEquals(criarFilmeDTO.getNomeFilme(), response.getNomeFilme());
        assertEquals(criarFilmeDTO.getGenero(), response.getGenero());
        assertEquals(criarFilmeDTO.getNota(), response.getNota());
        assertEquals(criarFilmeDTO.getComentario(), response.getComentario());
        assertEquals(criarFilmeDTO.getCorAvaliacao(), response.getCorAvaliacao());
    }

}