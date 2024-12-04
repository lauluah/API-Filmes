package com.adatech.filmes_API.unit.service.FilmeService;

import com.adatech.filmes_API.exception.FilmeNaoEncontradoException;
import com.adatech.filmes_API.repository.FilmeRepository;
import com.adatech.filmes_API.service.FilmeService.DeletarFilmeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DeletarFilmeServiceTest {

    @InjectMocks
    DeletarFilmeService deletarFilmeService;

    @Mock
    FilmeRepository filmeRepository;

    @Test
    void filmeExistente_deletarFilme_obtenhoSucesso() {
        Long filmeId = 1L;

        when(filmeRepository.existsById(filmeId)).thenReturn(true);
        doNothing().when(filmeRepository).deleteById(filmeId);

        ResponseEntity<String> response = deletarFilmeService.deletarFilme(filmeId);

        assertEquals("Filme deletado com sucesso.", response.getBody());
        assertEquals(200, response.getStatusCode().value());
    }

    @Test
    void filmeNaoExistente_deletarFilme_obtenhoFalha() {
        Long filmeId = 1L;

        when(filmeRepository.existsById(filmeId)).thenReturn(false);

        assertThrows(FilmeNaoEncontradoException.class, () -> deletarFilmeService.deletarFilme(filmeId));
    }
}