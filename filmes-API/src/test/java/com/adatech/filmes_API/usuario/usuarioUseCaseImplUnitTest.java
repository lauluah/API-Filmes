package com.adatech.filmes_API.usuario;

import com.adatech.filmes_API.model.Usuario;
import com.adatech.filmes_API.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc
public class usuarioUseCaseImplUnitTest {

    @MockBean
    private UsuarioRepository usuarioRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void ClientePorEmail_realizoBusca_deveRetornarCliente() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setEmail("novo@email.com");

        Mockito.when(usuarioRepository.findByEmail("novo@email.com")).thenReturn(Optional.of(usuario));
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/usuarios/email?email=novo@email.com")
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(
                        MockMvcResultHandlers.print()
                ).andExpect(
                        MockMvcResultMatchers.status().isOk()
                );
    }

    @Test
    public void ClientePorCPF_realizoBusca_deveRetornarCliente() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setCpf("153.123.123-61");

        Mockito.when(usuarioRepository.findByCpf("153.123.123-61")).thenReturn(Optional.of(usuario));
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/usuarios/cpf?cpf=153.123.123-61")
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(
                        MockMvcResultHandlers.print()
                ).andExpect(
                        MockMvcResultMatchers.status().isOk()
                );
    }
}
