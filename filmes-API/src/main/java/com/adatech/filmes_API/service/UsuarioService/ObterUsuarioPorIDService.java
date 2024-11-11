package com.adatech.filmes_API.service.UsuarioService;


import com.adatech.filmes_API.exception.UsuarioNaoEncontradoException;
import com.adatech.filmes_API.model.Usuario;
import com.adatech.filmes_API.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class ObterUsuarioPorIDService {

    private final UsuarioRepository repository;

    public ObterUsuarioPorIDService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public Usuario execute(Long id) {
        return repository.findById(id).orElseThrow(() -> new UsuarioNaoEncontradoException(String.format("Não foi possível encontrar usuário com id %s", id)));
    }

}
