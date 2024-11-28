package com.adatech.filmes_API.unit.controller;

import com.adatech.filmes_API.controller.UsuarioController;
import com.adatech.filmes_API.model.Usuario;
import com.adatech.filmes_API.service.UsuarioService.ObterUsuariosPorFiltroService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

@ExtendWith(MockitoExtension.class)
public class UsuarioControllerUnitTest {

    @Mock
    private ObterUsuariosPorFiltroService usuarioService;

    @InjectMocks
    private UsuarioController controller;

    @Test
    public void clienteExistente_buscarPorNome_deveRetornarCliente() {
        var usuarios = IntStream.range(0, 20)
                .mapToObj(index -> {
                    var usuario = new Usuario();
                    usuario.setNome("luisa  " + index);
                    return usuario;
                })
                .collect(Collectors.toList());

        Mockito.when(usuarioService.obterUsuarioPorNome("luisa")).thenReturn(usuarios);

        var listaDeUsuarios = controller.obterUsuariosPorNome("luisa");

        Assertions.assertEquals(usuarios.size(), listaDeUsuarios.size());
        Assertions.assertEquals(usuarios.getFirst().getNome(), listaDeUsuarios.getFirst().getNome());
    }
}