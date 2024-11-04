package com.adatech.filmes_API.service;


import com.adatech.filmes_API.model.Usuario;
import com.adatech.filmes_API.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class ObterUsuarioPorIdService {

    private final UsuarioRepository repository;

    public ObterUsuarioPorIdService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public Usuario execute(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException(String.format("Não foi possível encontrar usuário com id %s", id)));
    }

}
