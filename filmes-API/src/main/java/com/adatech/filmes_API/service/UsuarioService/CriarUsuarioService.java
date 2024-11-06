package com.adatech.filmes_API.service;


import com.adatech.filmes_API.dto.request.CriarUsuarioDTO;
import com.adatech.filmes_API.dto.mapper.UsuarioDTOMapper;
import com.adatech.filmes_API.dto.response.UsuarioResponseDTO;
import com.adatech.filmes_API.model.Usuario;
import com.adatech.filmes_API.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class CriarUsuarioService {
    private final UsuarioRepository repository;

    public CriarUsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public UsuarioResponseDTO execute(CriarUsuarioDTO usuarioQueSeraSalvo) {
        Usuario usuarioEntity = UsuarioDTOMapper.toEntity(usuarioQueSeraSalvo);
        Usuario usuarioPersistido = repository.save(usuarioEntity);
        return UsuarioDTOMapper.toResponse(usuarioPersistido);
    }
}
