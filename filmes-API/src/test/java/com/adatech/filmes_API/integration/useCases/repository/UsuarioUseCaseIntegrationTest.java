package com.adatech.filmes_API.integration.useCases.repository;

import com.adatech.filmes_API.dto.request.CriarUsuarioDTO;
import com.adatech.filmes_API.exception.CpfDuplicadoException;
import com.adatech.filmes_API.exception.EmailDuplicadoException;
import com.adatech.filmes_API.exception.UsuarioNaoEncontradoException;
import com.adatech.filmes_API.model.Usuario;
import com.adatech.filmes_API.repository.UsuarioRepository;
import com.adatech.filmes_API.service.UsuarioService.CriarUsuarioService;
import org.junit.jupiter.api.BeforeEach;
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

import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class  UsuarioUseCaseIntegrationTest {

    @MockBean
    private UsuarioRepository usuarioRepository;

    @MockBean
    private CriarUsuarioService criarUsuarioService;

    @Autowired
    private MockMvc mockMvc;

    private Usuario usuario;

    @BeforeEach
    public void setUp() {
        usuario = new Usuario();
        usuario.setEmail("teste@gmail.com");
        usuario.setPassword("teste123");
        usuario.setCpf("273.418.830-90");
    }

    @Test
    public void buscarUsuarioPorEmail_realizoBusca_deveRetornarUsuario() throws Exception {
        Mockito.when(usuarioRepository.findByEmail("teste@gmail.com")).thenReturn(Optional.of(usuario));

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/usuarios/email?email=teste@gmail.com")
                                .accept(MediaType.APPLICATION_JSON)
                                .header(HttpHeaders.AUTHORIZATION, "Basic dGVzdGVAZ21haWwuY29tOnRlc3RlMTIz")
                ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }

    @Test
    public void usuarioPorCPF_realizoBusca_deveRetornarUsuario() throws Exception {
        Mockito.when(usuarioRepository.findByEmail("teste@gmail.com")).thenReturn(Optional.of(usuario));
        Mockito.when(usuarioRepository.findByCpf("686.629.790-76")).thenReturn(Optional.of(usuario));

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/usuarios/cpf?cpf=686.629.790-76")
                                .accept(MediaType.APPLICATION_JSON)
                                .header(HttpHeaders.AUTHORIZATION, "Basic dGVzdGVAZ21haWwuY29tOnRlc3RlMTIz")
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }

    @Test
    public void usuarioNaoExiste_realizadoOCadastro_devoObterSucesso() throws Exception {
        var usuarioJson = """
                    {
                          "nome": "teste",
                          "idade": 22,
                          "email": "teste@gmail.com",
                          "password": "teste123",
                          "cpf": "686.629.790-76"
                      }
                """;

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/usuarios")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(usuarioJson)
                ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void usuarioNaoExiste_realizadoOCadastroSemNome_devoObterFalha() throws Exception {
        var usuarioJson = """
                    {
                          "nome": " ",
                          "idade": 22,
                          "email": "teste@gmail.com",
                          "password": "teste123",
                          "cpf": "686.629.790-76"
                      }
                """;

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/usuarios")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(usuarioJson)
                ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.mensagem").value("Erro de validação nos campos"));
    }

    @Test
    public void usuarioNaoExiste_realizadoOCadastroComNomeMenorQueTres_devoObterFalha() throws Exception {
        var usuarioJson = """
                    {
                          "nome": "te",
                          "idade": 22,
                          "email": "teste@gmail.com",
                          "password": "teste123",
                          "cpf": "686.629.790-76"
                      }
                """;

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/usuarios")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(usuarioJson)
                ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.mensagem").value("Erro de validação nos campos"));
    }

    @Test
    public void usuarioNaoExiste_realizadoOCadastroSemEmail_devoObterFalha() throws Exception {
        var usuarioJson = """
                    {
                          "nome": "teste",
                          "idade": 22,
                          "email": "",
                          "password": "teste123",
                          "cpf": "686.629.790-76"
                      }
                """;

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/usuarios")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(usuarioJson)
                ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.mensagem").value("Erro de validação nos campos"));
    }

    @Test
    public void cadastroUsuario_comEmailInvalido_deveRetornarErro() throws Exception {
        var usuarioJson = """
                    {
                          "nome": "teste",
                          "idade": 22,
                          "email": "test.gmailcom",
                          "password": "teste123",
                          "cpf": "686.629.790-76"
                      }
                """;

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/usuarios")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(usuarioJson)
                ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.mensagem").value("Erro de validação nos campos"));
    }

    @Test
    public void UsuarioPorEmail_naoEncontrado_deveRetornarErro() throws Exception {
        Mockito.when(usuarioRepository.findByEmail("teste@gmail.com"))
                .thenReturn(Optional.of(usuario))
                .thenThrow(new UsuarioNaoEncontradoException("Não foi possível encontrar usuário com o email"));

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/usuarios/email?email=teste@gmail.com")
                                .accept(MediaType.APPLICATION_JSON)
                                .header(HttpHeaders.AUTHORIZATION, "Basic dGVzdGVAZ21haWwuY29tOnRlc3RlMTIz")
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void UsuarioPorNome_realizoBusca_deveRetornarUsuarios() throws Exception {
        usuario.setNome("teste");
        Mockito.when(usuarioRepository.findByEmail("teste@gmail.com")).thenReturn(Optional.of(usuario));
        Mockito.when(usuarioRepository.findByNomeContaining("teste")).thenReturn(List.of(usuario));

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/usuarios/nome?nome=teste")
                                .accept(MediaType.APPLICATION_JSON)
                                .header(HttpHeaders.AUTHORIZATION, "Basic dGVzdGVAZ21haWwuY29tOnRlc3RlMTIz")
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].nome").value("teste"));
    }

    @Test
    public void cadastroUsuario_quandoCpfInvalido_deveRetornarErro() throws Exception {
        var usuarioJson = """
                {
                      "nome": "teste",
                      "idade": 22,
                      "email": "teste@gmail.com",
                      "password": "teste123",
                      "cpf": "123"
                  }
            """;

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/usuarios")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(usuarioJson)
                ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.mensagem").value("Erro de validação nos campos"));
    }

    @Test
    public void deletarUsuario_quandoNaoEncontrado_deveRetornarErro() throws Exception {
        Mockito.when(usuarioRepository.findByEmail("teste@gmail.com")).thenReturn(Optional.of(usuario));

        Mockito.doThrow(new UsuarioNaoEncontradoException("Usuário não encontrado."))
                .when(usuarioRepository).deleteById(999L);

        mockMvc.perform(
                        MockMvcRequestBuilders.delete("/usuarios/999")
                                .accept(MediaType.APPLICATION_JSON)
                                .header(HttpHeaders.AUTHORIZATION, "Basic dGVzdGVAZ21haWwuY29tOnRlc3RlMTIz")
                ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void cadastroCliente_comEmailDuplicado_deveRetornarErro() throws Exception {
        var usuarioJson = """
                    {
                          "nome": "Teste",
                          "idade": 22,
                          "email": "teste@gmail.com",
                          "password": "teste123",
                          "cpf": "686.629.790-76"
                      }
                """;

        Mockito.when(criarUsuarioService.execute(Mockito.any(CriarUsuarioDTO.class)))
                .thenThrow(new EmailDuplicadoException("Email já cadastrado no sistema."));

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/usuarios")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(usuarioJson)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isConflict())
                .andExpect(MockMvcResultMatchers.jsonPath("$.mensagem").value("Email já cadastrado no sistema."));
    }

    @Test
    public void cadastroCliente_comCpfDuplicado_deveRetornarErro() throws Exception {
        var usuarioJson = """
                    {
                          "nome": "Teste",
                          "idade": 22,
                          "email": "teste@gmail.com",
                          "password": "teste123",
                          "cpf": "686.629.790-76"
                      }
                """;

        Mockito.when(criarUsuarioService.execute(Mockito.any(CriarUsuarioDTO.class)))
                .thenThrow(new CpfDuplicadoException("Cpf já cadastrado no sistema."));

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/usuarios")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(usuarioJson)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isConflict())
                .andExpect(MockMvcResultMatchers.jsonPath("$.mensagem").value("Cpf já cadastrado no sistema."));
    }
}
