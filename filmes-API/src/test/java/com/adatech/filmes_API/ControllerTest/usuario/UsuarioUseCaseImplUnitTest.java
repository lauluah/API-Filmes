package com.adatech.filmes_API.ControllerTest.usuario;

import com.adatech.filmes_API.model.Usuario;
import com.adatech.filmes_API.repository.UsuarioRepository;
import com.adatech.filmes_API.service.UsuarioService.CriarUsuarioService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UsuarioUseCaseImplUnitTest {

    @MockBean
    private UsuarioRepository usuarioRepository;

    @MockBean
    private CriarUsuarioService criarUsuarioService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void ClientePorEmail_realizoBusca_deveRetornarCliente() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setEmail("laura123@gmail.com");
        usuario.setPassword("laurinha");

        Mockito.when(usuarioRepository.findByEmail("laura123@gmail.com")).thenReturn(Optional.of(usuario));
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/usuarios/email?email=laura123@gmail.com")
                                .accept(MediaType.APPLICATION_JSON)
                                .header(HttpHeaders.AUTHORIZATION, "Basic bGF1cmExMjNAZ21haWwuY29tOmxhdXJpbmhh")
                )
                .andDo(
                        MockMvcResultHandlers.print()
                ).andExpect(
                        status().isOk()
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
                        status().isOk()
                );
    }

    @Test
    public void clienteNaoExiste_realizadoOCadastro_devoObterSucesso() throws Exception {
        var usuarioJson = """
                    {
                          "nome": "Laura",
                          "idade": 20,
                          "email": "laura123@gmail.com",
                          "password": "laurinha",
                          "cpf": "27341883090"
                      }
                """;

        mockMvc.perform(
                MockMvcRequestBuilders.post("/usuarios")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(usuarioJson)
        ).andDo(
                MockMvcResultHandlers.print()
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );
    }

    @Test
    public void clienteNaoExiste_realizadoOCadastroSemNome_devoObterFalha() throws Exception {
        var usuarioJson = """
                    {
                          "nome": "",
                          "idade": 20,
                          "email": "laura123@gmail.com",
                          "password": "laurinha",
                          "cpf": "27341883090"
                      }
                """;

        mockMvc.perform(
                MockMvcRequestBuilders.post("/usuarios")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(usuarioJson)
        ).andDo(
                MockMvcResultHandlers.print()
        ).andExpect(
                MockMvcResultMatchers.status().isBadRequest()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.mensagem").value("Erro de validação nos campos")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.errors.nome").value("O nome é obrigatório e não pode estar em branco.")
        );
    }

    @Test
    public void clienteNaoExiste_realizadoOCadastroComNomeMenorQueTres_devoObterFalha() throws Exception {
        var usuarioJson = """
                    {
                          "nome": "la",
                          "idade": 20,
                          "email": "laura123@gmail.com",
                          "password": "laurinha",
                          "cpf": "27341883090"
                      }
                """;

        mockMvc.perform(
                MockMvcRequestBuilders.post("/usuarios")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(usuarioJson)
        ).andDo(
                MockMvcResultHandlers.print()
        ).andExpect(
                MockMvcResultMatchers.status().isBadRequest()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.mensagem").value("Erro de validação nos campos")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.errors.nome").value("O nome deve ter entre 3 e 50 caracteres.")
        );
    }

    @Test
    public void clienteNaoExiste_realizadoOCadastroSemEmail_devoObterFalha() throws Exception {
        var usuarioJson = """
                    {
                          "nome": "laura",
                          "idade": 20,
                          "email": "",
                          "password": "laurinha",
                          "cpf": "27341883090"
                      }
                """;

        mockMvc.perform(
                MockMvcRequestBuilders.post("/usuarios")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(usuarioJson)
        ).andDo(
                MockMvcResultHandlers.print()
        ).andExpect(
                MockMvcResultMatchers.status().isBadRequest()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.mensagem").value("Erro de validação nos campos")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.errors.email").value("O email é obrigatório e não pode estar em branco.")
        );
    }

    @Test
    public void cadastroCliente_comEmailInvalido_deveRetornarErro() throws Exception {
        var usuarioJson = """
                    {
                          "nome": "laura",
                          "idade": 20,
                          "email": "laura.gmailcom",
                          "password": "laurinha",
                          "cpf": "27341883090"
                      }
                """;

        mockMvc.perform(
                MockMvcRequestBuilders.post("/usuarios")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(usuarioJson)
        ).andDo(
                MockMvcResultHandlers.print()
        ).andExpect(
                MockMvcResultMatchers.status().isBadRequest()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.mensagem").value("Erro de validação nos campos")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.errors.email").value("O formato do email está inválido. Verifique se o email está correto.")
        );
    }
}
