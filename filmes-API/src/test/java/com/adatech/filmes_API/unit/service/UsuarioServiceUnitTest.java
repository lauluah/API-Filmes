package com.adatech.filmes_API.unit.service;

import com.adatech.filmes_API.dto.mapper.UsuarioDTOMapper;
import com.adatech.filmes_API.dto.request.CriarUsuarioDTO;
import com.adatech.filmes_API.dto.response.UsuarioResponseDTO;
import com.adatech.filmes_API.exception.EmailDuplicadoException;
import com.adatech.filmes_API.exception.UsuarioNaoEncontradoException;
import com.adatech.filmes_API.model.Usuario;
import com.adatech.filmes_API.repository.UsuarioRepository;
import com.adatech.filmes_API.service.UsuarioService.CriarUsuarioService;
import com.adatech.filmes_API.service.UsuarioService.DeletarUsuarioService;
import com.adatech.filmes_API.service.UsuarioService.ObterUsuarioPorIDService;
import com.adatech.filmes_API.service.UsuarioService.ObterUsuariosPorFiltroService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceUnitTest {
    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private CriarUsuarioService criarUsuarioService;
    @InjectMocks
    private DeletarUsuarioService deletarUsuarioService;
    @InjectMocks
    private ObterUsuariosPorFiltroService obterUsuariosPorFiltroService;
    @InjectMocks
    private ObterUsuarioPorIDService obterUsuarioPorIDService;

    CriarUsuarioDTO usuarioDTO;

    @BeforeEach
    void setUp() {
        usuarioDTO = new CriarUsuarioDTO("Teste", 22, "teste@gmail.com", "teste123", "686.629.790-76");
    }

    @Test
    public void criarUsuario_quandoUsuarioValido_deveSalvarNoBanco() {
        var usuario = UsuarioDTOMapper.toEntity(usuarioDTO);

        Mockito.when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

        UsuarioResponseDTO resultado = criarUsuarioService.execute(usuarioDTO);

        Assertions.assertNotNull(resultado);
        Assertions.assertEquals("teste@gmail.com", resultado.getEmail());
    }

    @Test
    public void deletarUsuario_quandoUsuarioExiste_deveDeletarComSucesso() {
        Long usuarioId = 1L;

        Mockito.when(usuarioRepository.existsById(usuarioId)).thenReturn(true);

        var response = deletarUsuarioService.deletarUsuario(usuarioId);

        Assertions.assertNotNull(response);
        Assertions.assertEquals("Usuário deletado com sucesso.", response.getBody());
        verify(usuarioRepository, Mockito.times(1)).deleteById(usuarioId);
    }

    @Test
    public void deletarUsuario_quandoUsuarioNaoExiste_deveLancarExcecao() {
        Long usuarioId = 1L;

        Mockito.when(usuarioRepository.existsById(usuarioId)).thenReturn(false);

        Assertions.assertThrows(UsuarioNaoEncontradoException.class, () -> {
            deletarUsuarioService.deletarUsuario(usuarioId);
        });

        verify(usuarioRepository, never()).deleteById(usuarioId);
    }

    @Test
    public void obterUsuarioPorID_quandoUsuarioExiste_deveRetornarUsuario() {
        Long usuarioId = 1L;
        Usuario usuario = new Usuario();
        usuario.setId(usuarioId);
        usuario.setNome("Teste");

        Mockito.when(usuarioRepository.findById(usuarioId)).thenReturn(Optional.of(usuario));

        Usuario resultado = obterUsuarioPorIDService.execute(usuarioId);

        Assertions.assertNotNull(resultado);
        Assertions.assertEquals(usuarioId, resultado.getId());
        Assertions.assertEquals("Teste", resultado.getNome());
    }

    @Test
    public void obterUsuarioPorID_quandoUsuarioNaoExiste_deveLancarExcecao() {
        Long usuarioId = 1L;

        Mockito.when(usuarioRepository.findById(usuarioId)).thenReturn(Optional.empty());

        Assertions.assertThrows(UsuarioNaoEncontradoException.class, () -> {
            obterUsuarioPorIDService.execute(usuarioId);
        });
    }

    @Test
    public void obterUsuarioPorEmail_quandoEmailExiste_deveRetornarUsuario() {
        String email = "teste@gmail.com";
        Usuario usuario = new Usuario();
        usuario.setEmail(email);

        Mockito.when(usuarioRepository.findByEmail(email)).thenReturn(Optional.of(usuario));

        Usuario resultado = obterUsuariosPorFiltroService.obterUsuarioPorEmail(email);

        Assertions.assertNotNull(resultado);
        Assertions.assertEquals(email, resultado.getEmail());
    }

    @Test
    public void obterUsuarioPorEmail_quandoEmailNaoExiste_deveLancarExcecao() {
        String email = "teste@gmail.com";

        Mockito.when(usuarioRepository.findByEmail(email)).thenReturn(Optional.empty());

        Assertions.assertThrows(UsuarioNaoEncontradoException.class, () -> {
            obterUsuariosPorFiltroService.obterUsuarioPorEmail(email);
        });
    }

    @Test
    public void obterUsuarioPorCpf_quandoCpfExiste_deveRetornarUsuario() {
        String cpf = "123.456.789-00";
        Usuario usuario = new Usuario();
        usuario.setCpf(cpf);

        Mockito.when(usuarioRepository.findByCpf(cpf)).thenReturn(Optional.of(usuario));

        Usuario resultado = obterUsuariosPorFiltroService.obterUsuarioPorCpf(cpf);

        Assertions.assertNotNull(resultado);
        Assertions.assertEquals(cpf, resultado.getCpf());
    }

    @Test
    public void obterUsuarioPorCpf_quandoCpfNaoExiste_deveLancarExcecao() {
        String cpf = "000.000.000-00";

        Mockito.when(usuarioRepository.findByCpf(cpf)).thenReturn(Optional.empty());

        Assertions.assertThrows(UsuarioNaoEncontradoException.class, () -> {
            obterUsuariosPorFiltroService.obterUsuarioPorCpf(cpf);
        });
    }

    @Test
    public void obterUsuarioPorNome_quandoNomeExiste_deveRetornarUsuarios() {
        String nome = "Teste";
        Usuario usuario1 = new Usuario();
        usuario1.setNome("Teste Um");
        Usuario usuario2 = new Usuario();
        usuario2.setNome("Teste Dois");

        List<Usuario> usuarios = List.of(usuario1, usuario2);

        Mockito.when(usuarioRepository.findByNomeContaining(nome)).thenReturn(usuarios);

        List<Usuario> resultado = obterUsuariosPorFiltroService.obterUsuarioPorNome(nome);

        Assertions.assertNotNull(resultado);
        Assertions.assertEquals(2, resultado.size());
        Assertions.assertEquals("Teste Um", resultado.get(0).getNome());
        Assertions.assertEquals("Teste Dois", resultado.get(1).getNome());
    }

    @Test
    public void obterUsuarioPorNome_quandoNomeNaoExiste_deveLancarExcecao() {
        String nome = "Teste";

        Mockito.when(usuarioRepository.findByNomeContaining(nome)).thenReturn(List.of());

        Assertions.assertThrows(UsuarioNaoEncontradoException.class, () -> {
            obterUsuariosPorFiltroService.obterUsuarioPorNome(nome);
        });
    }

    @Test
    void criarUsuario_quandoEmailDuplicado_deveLancarExcecao() {
        Mockito.when(usuarioRepository.existsByEmail("teste@gmail.com")).thenReturn(true);

        Assertions.assertThrows(EmailDuplicadoException.class, () -> {
            criarUsuarioService.execute(usuarioDTO);
        });

        verify(usuarioRepository, never()).save(any(Usuario.class));
    }

}
