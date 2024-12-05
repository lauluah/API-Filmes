package com.adatech.filmes_API.unit.repository;

import com.adatech.filmes_API.exception.UsuarioNaoEncontradoException;
import com.adatech.filmes_API.model.Usuario;
import com.adatech.filmes_API.repository.UsuarioRepository;
import com.adatech.filmes_API.service.UsuarioService.ObterUsuariosPorFiltroService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class UsuarioRepositoryUnitTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private ObterUsuariosPorFiltroService obterUsuariosPorFiltroService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void buscarUsuarioPorCpf_quandoEncontrado_deveRetornarUsuario() {
        Usuario mockUsuario = new Usuario();
        mockUsuario.setCpf("12345678901");
        mockUsuario.setNome("Teste");
        when(usuarioRepository.findByCpf("12345678901")).thenReturn(Optional.of(mockUsuario));

        Usuario result = obterUsuariosPorFiltroService.obterUsuarioPorCpf("12345678901");

        assertEquals("Teste", result.getNome());
        assertEquals("12345678901", result.getCpf());
    }

    @Test
    void buscarUsuarioPorCpf_quandoNaoEncontrado_deveLancarExcecao() {
        when(usuarioRepository.findByCpf("98765432100")).thenReturn(Optional.empty());

        assertThrows(UsuarioNaoEncontradoException.class, () -> obterUsuariosPorFiltroService.obterUsuarioPorCpf("98765432100"));
    }

    @Test
    void buscarUsuarioPorEmail_quandoEncontrado_deveRetornarUsuario() {
        Usuario mockUsuario = new Usuario();
        mockUsuario.setEmail("teste@email.com");
        Mockito.when(usuarioRepository.findByEmail("teste@email.com")).thenReturn(Optional.of(mockUsuario));

        Usuario usuario = obterUsuariosPorFiltroService.obterUsuarioPorEmail("teste@email.com");

        assertNotNull(usuario);
        assertEquals("teste@email.com", usuario.getEmail());
    }

    @Test
    void buscarUsuarioPorEmail_quandoNaoEncontrado_deveLancarExcecao() {
        when(usuarioRepository.findByEmail("teste@gmail.com")).thenReturn(Optional.empty());

        UsuarioNaoEncontradoException exception = assertThrows(
                UsuarioNaoEncontradoException.class,
                () -> obterUsuariosPorFiltroService.obterUsuarioPorEmail("teste@gmail.com")
        );

        assertEquals("Não foi possível encontrar usuário com o email teste@gmail.com", exception.getMessage());
    }

    @Test
    void buscarUsuarioPorNome_quandoEncontrado_deveRetornarUsuario() {
        Usuario mockUsuario = new Usuario();
        mockUsuario.setNome("Teste 1");
        when(usuarioRepository.findByNomeContaining("Teste")).thenReturn(List.of(mockUsuario));

        List<Usuario> usuarios = obterUsuariosPorFiltroService.obterUsuarioPorNome("Teste");

        assertNotNull(usuarios);
        assertEquals(1, usuarios.size());
        assertEquals("Teste 1", usuarios.get(0).getNome());
    }

    @Test
    void buscarUsuarioPorNome_quandoNaoEncontrado_deveLancarExcecao() {
        when(usuarioRepository.findByNomeContaining("Teste")).thenReturn(Collections.emptyList());

        UsuarioNaoEncontradoException exception = assertThrows(
                UsuarioNaoEncontradoException.class,
                () -> obterUsuariosPorFiltroService.obterUsuarioPorNome("Teste")
        );

        assertEquals("Não foi possível encontrar usuários com o nome Teste", exception.getMessage());
    }
}
