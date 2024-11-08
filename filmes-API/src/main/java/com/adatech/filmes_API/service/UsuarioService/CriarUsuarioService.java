package com.adatech.filmes_API.service.UsuarioService;

import com.adatech.filmes_API.dto.request.CriarUsuarioDTO;
import com.adatech.filmes_API.dto.mapper.UsuarioDTOMapper;
import com.adatech.filmes_API.dto.response.UsuarioResponseDTO;
import com.adatech.filmes_API.model.Usuario;
import com.adatech.filmes_API.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class CriarUsuarioService {
    private final UsuarioRepository UsuarioRepository;

    public CriarUsuarioService(UsuarioRepository UsuarioRepository) {
        this.UsuarioRepository = UsuarioRepository;
    }

    public UsuarioResponseDTO execute(CriarUsuarioDTO usuarioParaSalvar) {
        Usuario usuarioEntity = UsuarioDTOMapper.toEntity(usuarioParaSalvar);
        Usuario usuarioPersistido = UsuarioRepository.save(usuarioEntity);
        return UsuarioDTOMapper.toResponse(usuarioPersistido);
    }
}