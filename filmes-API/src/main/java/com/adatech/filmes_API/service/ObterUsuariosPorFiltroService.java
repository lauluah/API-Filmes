package com.adatech.filmes_API.service;

import com.adatech.filmes_API.model.Usuario;
import com.adatech.filmes_API.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ObterUsuariosPorFiltroService {
    private final UsuarioRepository repository;

    public ObterUsuariosPorFiltroService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public List<Usuario> obterUsuarioPorGenero(String genero) {
        List<Usuario> usuarioPorGenero = repository.findByGeneroContaining(genero);
        return usuarioPorGenero;
        //tratar
    }

    public Usuario obterUsuarioPorEmail(String email) {
        return repository.findByEmail(email).orElseThrow(() -> new RuntimeException(String.format("Não foi possível encontrar usuário com o email %s", email)));
    }

    public Usuario obterUsuarioPorCpf(String cpf) {
        return repository.findByCpf(cpf).orElseThrow(() -> new RuntimeException(String.format("Não foi possível encontrar usuário com cpf %s", cpf)));
    }

    public List<Usuario> obterUsuarioPorNome(String nomes) {
        return repository.findByNomeContaining(nomes);
    }
}
