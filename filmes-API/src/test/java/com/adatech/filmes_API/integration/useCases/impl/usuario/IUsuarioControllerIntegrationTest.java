package com.adatech.filmes_API.integration.useCases.impl.usuario;

import com.adatech.filmes_API.controller.UsuarioController;
import com.adatech.filmes_API.dto.request.CriarUsuarioDTO;
import com.adatech.filmes_API.dto.response.UsuarioResponseDTO;
import com.adatech.filmes_API.service.UsuarioService.CriarUsuarioService;
import com.adatech.filmes_API.model.Usuario;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class IUsuarioControllerIntegrationTest {

    @MockBean
    private UsuarioController usuarioController;

    @MockBean
    private CriarUsuarioService criarUsuarioService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void criarUsuario_deveRetornar201() throws Exception {
        CriarUsuarioDTO novoUsuario = new CriarUsuarioDTO("teste", 22, "teste@gmail.com", "teste123", "127.462.590-44");
        UsuarioResponseDTO usuarioResponse = new UsuarioResponseDTO("teste", 22, "teste@gmail.com");

        Mockito.when(criarUsuarioService.execute(novoUsuario)).thenReturn(usuarioResponse);

        mockMvc.perform(post("/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\": \"teste\", \"idade\": 22, \"email\": \"teste@gmail.com\", \"password\": \"teste123\", \"cpf\": \"127.462.590-44\"}")
                )
                .andExpect(status().isCreated())
                .andDo(print());
    }

}
