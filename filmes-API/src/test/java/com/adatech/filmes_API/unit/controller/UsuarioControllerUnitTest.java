package com.adatech.filmes_API.unit.controller;

import com.adatech.filmes_API.controller.UsuarioController;
import com.adatech.filmes_API.exception.UsuarioNaoEncontradoException;
import com.adatech.filmes_API.model.Usuario;
import com.adatech.filmes_API.service.UsuarioService.DeletarUsuarioService;
import com.adatech.filmes_API.service.UsuarioService.ObterUsuarioPorIDService;
import com.adatech.filmes_API.service.UsuarioService.ObterUsuariosPorFiltroService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

@ExtendWith(MockitoExtension.class)
public class UsuarioControllerUnitTest {

    @Mock
    private ObterUsuariosPorFiltroService usuarioServiceFiltros;
    @Mock
    private DeletarUsuarioService deletarUsuarioService;
    @Mock
    private ObterUsuarioPorIDService obterUsuarioPorIDService;

    @InjectMocks
    private UsuarioController controller;

    @Test
    public void clienteExistente_buscarPorNome_deveRetornarCliente() {
        var usuarios = IntStream.range(0, 10)
                .mapToObj(index -> {
                    var usuario = new Usuario();
                    usuario.setNome("teste  " + index);
                    return usuario;
                })
                .collect(Collectors.toList());

        Mockito.when(usuarioServiceFiltros.obterUsuarioPorNome("teste")).thenReturn(usuarios);

        var listaDeUsuarios = controller.obterUsuariosPorNome("teste");

        Assertions.assertEquals(usuarios.size(), listaDeUsuarios.size());
        Assertions.assertEquals(usuarios.get(0).getNome(), listaDeUsuarios.get(0).getNome());
    }
    @Test
    public void obterUsuarioPorNome_quandoNomeInexistente_deveLancarExcecao() {
        var nome = "teste";
        Mockito.when(usuarioServiceFiltros.obterUsuarioPorNome(nome))
                .thenThrow(new UsuarioNaoEncontradoException("Não foi possível encontrar usuários com o nome " + nome));

        Assertions.assertThrows(UsuarioNaoEncontradoException.class, () -> {
            controller.obterUsuariosPorNome(nome);
        });
    }

    @Test
    public void deletarUsuario_quandoIdExistente_deveRetornarMensagemSucesso() {
        var mensagem = ResponseEntity.ok("Usuário deletado com sucesso.");
        Mockito.when(deletarUsuarioService.deletarUsuario(1L)).thenReturn(mensagem);

        var resposta = controller.deletarUsuario(1L);

        Assertions.assertEquals("Usuário deletado com sucesso.", resposta.getBody());
    }

    @Test
    public void deletarUsuario_quandoNaoExistir_deveRetornarExcecao() {
        Mockito.when(deletarUsuarioService.deletarUsuario(2L)).thenThrow(new UsuarioNaoEncontradoException("Usuário não encontrado."));

        Assertions.assertThrows(UsuarioNaoEncontradoException.class, () -> {
            controller.deletarUsuario(2L);
        });
    }

    @Test
    public void obterUsuarioPorNome_quandoNaoEncontrar_deveRetornarExcecao() {
        Mockito.when(usuarioServiceFiltros.obterUsuarioPorNome("luisa")).thenThrow(new UsuarioNaoEncontradoException("Não foi possível encontrar usuários com o nome %s"));

        Assertions.assertThrows(UsuarioNaoEncontradoException.class, () -> {
            controller.obterUsuariosPorNome("luisa");
        });
    }

    @Test
    public void obterUsuarioPorEmail_quandoEmailExistente_deveRetornarUsuario() {
        var email = "email@teste.com";
        var usuario = new Usuario();

        usuario.setEmail(email);
        usuario.setNome("Teste");

        Mockito.when(usuarioServiceFiltros.obterUsuarioPorEmail(email)).thenReturn(usuario);

        var resultado = controller.ObterUsuarioPorEmail(email);


        Assertions.assertNotNull(resultado);
        Assertions.assertEquals(usuario.getNome(), resultado.getNome());
        Assertions.assertEquals(usuario.getEmail(), resultado.getEmail());
    }

    @Test
    public void obterUsuarioPorEmail_quandoEmailInexistente_deveLancarExcecao() {
        var email = "naoexiste@teste.com";
        Mockito.when(usuarioServiceFiltros.obterUsuarioPorEmail(email))
                .thenThrow(new UsuarioNaoEncontradoException("Não foi possível encontrar usuário com o email " + email));

        Assertions.assertThrows(UsuarioNaoEncontradoException.class, () -> {
            controller.ObterUsuarioPorEmail(email);
        });
    }

    @Test
    public void obterUsuarioPorCpf_quandoCpfExistente_deveRetornarUsuario() {
        var cpf = "12345678900";
        var usuario = new Usuario();

        usuario.setCpf(cpf);
        usuario.setNome("Teste");

        Mockito.when(usuarioServiceFiltros.obterUsuarioPorCpf(cpf)).thenReturn(usuario);
        var resultado = controller.obterUsuariosPorCpf(cpf);

        Assertions.assertNotNull(resultado);
        Assertions.assertEquals(usuario.getNome(), resultado.getNome());
        Assertions.assertEquals(usuario.getCpf(), resultado.getCpf());
    }

    @Test
    public void obterUsuarioPorCpf_quandoCpfInexistente_deveLancarExcecao() {
        var cpf = "12345678900";
        Mockito.when(usuarioServiceFiltros.obterUsuarioPorCpf(cpf))
                .thenThrow(new UsuarioNaoEncontradoException("Não foi possível encontrar usuário com cpf " + cpf));

        Assertions.assertThrows(UsuarioNaoEncontradoException.class, () -> {
            controller.obterUsuariosPorCpf(cpf);
        });
    }

    @Test
    public void obterUsuarioPorId_quandoIdExistente_deveRetornarUsuario() {
        Usuario usuario = new Usuario();
        usuario.setId(1L);

        Mockito.when(obterUsuarioPorIDService.execute(1L)).thenReturn(usuario);

        var resposta = controller.obterUsuarioPorId(1L);

        Assertions.assertEquals(200, resposta.getStatusCodeValue());
        Assertions.assertNotNull(resposta);
        Assertions.assertNotNull(resposta.getBody());

        Usuario usuarioResposta = (Usuario) resposta.getBody();

        Assertions.assertEquals(1L, usuarioResposta.getId());
    }

    @Test
    public void obterUsuarioPorId_quandoIdExistente_deveRetornarExcecao() {
        Usuario usuario = new Usuario();
        usuario.setId(2L);
        Mockito.when(obterUsuarioPorIDService.execute(2L)).thenThrow(new UsuarioNaoEncontradoException("Não foi possível encontrar usuário com id"));

        Assertions.assertThrows(UsuarioNaoEncontradoException.class, () -> {
            controller.obterUsuarioPorId(2L);
        });
    }

}