package com.adatech.filmes_API.service.UsuarioService;

import com.adatech.filmes_API.exception.UsuarioNaoEncontradoException;
import com.adatech.filmes_API.repository.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class DeletarUsuarioService {
    private final UsuarioRepository UsuarioRepository;

    public DeletarUsuarioService(UsuarioRepository UsuarioRepository) {
        this.UsuarioRepository = UsuarioRepository;
    }

    public ResponseEntity<String> deletarUsuario(Long id) {
        if (UsuarioRepository.existsById(id)) {
            UsuarioRepository.deleteById(id);
            return ResponseEntity.ok("Usuário deletado com sucesso.");
        }
        throw new UsuarioNaoEncontradoException("Usuário não encontrado.");
    }
}
