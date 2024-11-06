package com.adatech.filmes_API.service.UsuarioService;


import com.adatech.filmes_API.model.Usuario;
import com.adatech.filmes_API.repository.UsuarioRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ObterUsuariosService {
    private final UsuarioRepository usuarioRepository;

    public ObterUsuariosService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Page<Usuario> execute(Pageable pageable) {
        return usuarioRepository.findAll(pageable);
    }
}
