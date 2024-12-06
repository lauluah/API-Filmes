package com.adatech.filmes_API.integration.useCases.impl.usuario;

import com.adatech.filmes_API.controller.UsuarioController;
import com.adatech.filmes_API.dto.response.UsuarioResponseDTO;
import com.adatech.filmes_API.exception.UsuarioNaoEncontradoException;
import com.adatech.filmes_API.repository.UsuarioRepository;

import com.adatech.filmes_API.model.Usuario;
import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UsuarioControllerIntegrationTest {

    @MockBean
    private UsuarioController usuarioController;

    @MockBean
    private UsuarioRepository usuarioRepository;

    @Autowired
    private MockMvc mockMvc;

    private Usuario usuario;

    @BeforeEach
    public void setUp() {
        usuario = new Usuario();
        usuario.setNome("Teste");
        usuario.setId(1L);
        usuario.setEmail("teste@gmail.com");
        usuario.setCpf("273.418.830-90");
        usuario.setPassword("teste123");
    }

    @Test
    public void criarUsuario_deveRetornar201() throws Exception {
        UsuarioResponseDTO usuarioResponseDTO = new UsuarioResponseDTO("teste", 22, "teste@gmail.com");

        Mockito.when(usuarioController.criarUsuario(Mockito.any())).thenReturn(usuarioResponseDTO);

        mockMvc.perform(post("/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\": \"teste\", \"idade\": 22, \"email\": \"teste@gmail.com\", \"password\": \"teste123\", \"cpf\": \"127.462.590-44\"}")
                )
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    public void procurarPorId_quandoIdExistente_deveRetornarUsuario() throws Exception {
        Mockito.when(usuarioRepository.findByEmail("teste@gmail.com")).thenReturn(Optional.of(usuario));
        Mockito.when(usuarioController.obterUsuarioPorId(1L)).thenReturn(ResponseEntity.ok(usuario));

        mockMvc.perform(MockMvcRequestBuilders.get("/usuarios/1")
                        .header(HttpHeaders.AUTHORIZATION, "Basic dGVzdGVAZ21haWwuY29tOnRlc3RlMTIz")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nome").value("Teste"));
    }

    @Test
    public void procurarPorNome_quandoNomeExistente_deveRetornarListaDeUsuario() throws Exception {
        List<Usuario> usuarios = Collections.singletonList(usuario);

        Mockito.when(usuarioRepository.findByEmail("teste@gmail.com")).thenReturn(Optional.of(usuario));
        Mockito.when(usuarioController.obterUsuariosPorNome("Teste")).thenReturn(usuarios);

        mockMvc.perform(MockMvcRequestBuilders.get("/usuarios/nome?nome=Teste")
                        .header(HttpHeaders.AUTHORIZATION, "Basic dGVzdGVAZ21haWwuY29tOnRlc3RlMTIz")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        Assertions.assertEquals("Teste", usuario.getNome());
    }

    @Test
    public void procurarPorCpf_quandoCpfExistente_deveRetornarUsuario() throws Exception {
        Mockito.when(usuarioRepository.findByEmail("teste@gmail.com")).thenReturn(Optional.of(usuario));
        Mockito.when(usuarioController.obterUsuariosPorCpf("273.418.830-90")).thenReturn(usuario);

        mockMvc.perform(MockMvcRequestBuilders.get("/usuarios/cpf?cpf=273.418.830-90")
                        .header(HttpHeaders.AUTHORIZATION, "Basic dGVzdGVAZ21haWwuY29tOnRlc3RlMTIz")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cpf").value("273.418.830-90"));
    }

    @Test
    public void procurarPorEmail_quandoEmailExistente_deveRetornarUsuario() throws Exception {
        Mockito.when(usuarioRepository.findByEmail("teste@gmail.com")).thenReturn(Optional.of(usuario));
        Mockito.when(usuarioController.ObterUsuarioPorEmail("273.418.830-90")).thenReturn(ResponseEntity.ok(usuario));

        mockMvc.perform(MockMvcRequestBuilders.get("/usuarios/email?email=teste@gmail.com")
                        .header(HttpHeaders.AUTHORIZATION, "Basic dGVzdGVAZ21haWwuY29tOnRlc3RlMTIz")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        Assertions.assertEquals("teste@gmail.com", usuario.getEmail());
    }

    @Test
    public void deletarUsuarioPorId_quandoUsuarioExistente_deveRetornar200() throws Exception {
        Mockito.when(usuarioRepository.findByEmail("teste@gmail.com")).thenReturn(Optional.of(usuario));
        Mockito.when(usuarioController.deletarUsuario(1L)).thenReturn(ResponseEntity.ok("Usuário deletado com sucesso."));

        mockMvc.perform(MockMvcRequestBuilders.delete("/usuarios/1")
                        .header(HttpHeaders.AUTHORIZATION, "Basic dGVzdGVAZ21haWwuY29tOnRlc3RlMTIz")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Usuário deletado com sucesso."));
    }

    @Test
    public void procurarPorId_quandoIdNaoExistente_deveRetornar404() throws Exception {
        Mockito.when(usuarioRepository.findByEmail("teste@gmail.com")).thenReturn(Optional.of(usuario));
        Mockito.when(usuarioController.obterUsuarioPorId(999L)).thenThrow(new UsuarioNaoEncontradoException("Não foi possível encontrar usuário com id"));

        mockMvc.perform(MockMvcRequestBuilders.get("/usuarios/999")
                        .header(HttpHeaders.AUTHORIZATION, "Basic dGVzdGVAZ21haWwuY29tOnRlc3RlMTIz")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    public void procurarPorNome_quandoNomeNaoExistente_deveRetornar404() throws Exception {
        Mockito.when(usuarioRepository.findByEmail("teste@gmail.com")).thenReturn(Optional.of(usuario));
        Mockito.when(usuarioController.obterUsuariosPorNome("teste")).thenThrow(new UsuarioNaoEncontradoException("Não foi possível encontrar usuários com o nome"));

        mockMvc.perform(MockMvcRequestBuilders.get("/usuarios/nome?nome=teste")
                        .header(HttpHeaders.AUTHORIZATION, "Basic dGVzdGVAZ21haWwuY29tOnRlc3RlMTIz")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    public void procurarPorCpf_quandoCpfNaoExistente_deveRetornar404() throws Exception {
        Mockito.when(usuarioRepository.findByEmail("teste@gmail.com")).thenReturn(Optional.of(usuario));
        Mockito.when(usuarioController.obterUsuariosPorCpf("999.999.999-99")).thenThrow(new UsuarioNaoEncontradoException("Não foi possível encontrar usuário com cpf"));

        mockMvc.perform(MockMvcRequestBuilders.get("/usuarios/cpf?cpf=999.999.999-99")
                        .header(HttpHeaders.AUTHORIZATION, "Basic dGVzdGVAZ21haWwuY29tOnRlc3RlMTIz")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    public void procurarPorEmail_quandoEmailNaoExistente_deveRetornar404() throws Exception {
        Mockito.when(usuarioRepository.findByEmail("teste@gmail.com")).thenReturn(Optional.of(usuario));
        Mockito.when(usuarioController.ObterUsuarioPorEmail("naoexiste@gmail.com")).thenThrow(new UsuarioNaoEncontradoException("Não foi possível encontrar usuário com o email"));

        mockMvc.perform(MockMvcRequestBuilders.get("/usuarios/email?email=naoexiste@gmail.com")
                        .header(HttpHeaders.AUTHORIZATION, "Basic dGVzdGVAZ21haWwuY29tOnRlc3RlMTIz")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    public void deletarUsuarioPorId_quandoUsuarioNaoExistente_deveRetornar404() throws Exception {
        Mockito.when(usuarioRepository.findByEmail("teste@gmail.com")).thenReturn(Optional.of(usuario));
        Mockito.when(usuarioController.deletarUsuario(999L)).thenThrow(new UsuarioNaoEncontradoException("Usuário não encontrado."));

        mockMvc.perform(MockMvcRequestBuilders.delete("/usuarios/999")
                        .header(HttpHeaders.AUTHORIZATION, "Basic dGVzdGVAZ21haWwuY29tOnRlc3RlMTIz")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andDo(print());
    }
}