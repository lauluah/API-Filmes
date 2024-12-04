package com.adatech.filmes_API.integration.useCases.impl.filme;

import com.adatech.filmes_API.model.Filme;
import com.adatech.filmes_API.repository.FilmeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class FilmeIntegrationTest {

    @MockBean
    private FilmeRepository filmeRepository;

    @Autowired
    private MockMvc mockMvc;

    private Filme filme;

    @BeforeEach
    void setUp() {
        filme = new Filme(1L, "John Wick", "Ação", 8.5, "Excelente filme", "Verde");
    }

    @Test
    public void obterFilmePorId_deveRetornar200() throws Exception {
        Mockito.when(filmeRepository.findById(1L)).thenReturn(Optional.of(filme));

        mockMvc.perform(MockMvcRequestBuilders.get("/filmes/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void obterFilmePorId_naoExistente_deveRetornar404() throws Exception {
        Mockito.when(filmeRepository.findById(2L)).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.get("/filmes/2")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    public void obterFilmesPorNome_deveRetornar200() throws Exception {
        Mockito.when(filmeRepository.findByNomeFilmeContaining("John Wick")).thenReturn(List.of(filme));

        mockMvc.perform(MockMvcRequestBuilders.get("/filmes/nomeFilme")
                        .param("nomeFilme", "John Wick")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void obterFilmesPorNome_naoExistente_deveRetornar404() throws Exception {
        Mockito.when(filmeRepository.findByNomeFilmeContaining("Inexistente")).thenReturn(List.of());

        mockMvc.perform(MockMvcRequestBuilders.get("/filmes/nomeFilme")
                        .param("nomeFilme", "Inexistente")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    public void obterFilmesPorCorAvaliacao_deveRetornar200() throws Exception {
        Mockito.when(filmeRepository.findByCorAvaliacao("Verde")).thenReturn(List.of(filme));

        mockMvc.perform(MockMvcRequestBuilders.get("/filmes/corAvaliacao")
                        .param("corAvaliacao", "Verde")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void obterFilmesPorCorAvaliacao_naoExistente_deveRetornar404() throws Exception {
        Mockito.when(filmeRepository.findByCorAvaliacao("Inexistente")).thenReturn(List.of());

        mockMvc.perform(MockMvcRequestBuilders.get("/filmes/corAvaliacao")
                        .param("corAvaliacao", "Inexistente")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    public void deletarFilme_deveRetornar200() throws Exception {
        Mockito.when(filmeRepository.existsById(1L)).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.delete("/filmes/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void deletarFilme_naoExistente_deveRetornar404() throws Exception {
        Mockito.when(filmeRepository.existsById(2L)).thenReturn(false);

        mockMvc.perform(MockMvcRequestBuilders.delete("/filmes/2")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

}
