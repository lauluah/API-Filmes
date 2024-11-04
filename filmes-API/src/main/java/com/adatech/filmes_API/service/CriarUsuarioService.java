package com.adatech.filmes_API.service;


import com.adatech.filmes_API.model.Usuario;
import com.adatech.filmes_API.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class CriarUsuarioService {
    private final UsuarioRepository repository;

    public CriarUsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public Usuario execute(Usuario usuarioQueSeraSalvo) {
        Usuario usuarioPersistido = repository.save(usuarioQueSeraSalvo);
        return usuarioPersistido;
    }
}
