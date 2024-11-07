package com.adatech.filmes_API.service.UsuarioService;

import com.adatech.filmes_API.dto.request.CriarUsuarioDTO;
import com.adatech.filmes_API.dto.mapper.UsuarioDTOMapper;
import com.adatech.filmes_API.dto.response.FilmeResponseDTO;
import com.adatech.filmes_API.dto.response.UsuarioResponseDTO;
import com.adatech.filmes_API.model.Usuario;
import com.adatech.filmes_API.repository.UsuarioRepository;
import com.adatech.filmes_API.service.FilmeService.TMDbService;
import org.springframework.stereotype.Service;

@Service
public class CriarUsuarioService {
    private final UsuarioRepository UsuarioRepository;
    private final TMDbService tmdbService;

    public CriarUsuarioService(UsuarioRepository UsuarioRepository, TMDbService tmdbService) {
        this.UsuarioRepository = UsuarioRepository;
        this.tmdbService = tmdbService;
    }

    public UsuarioResponseDTO execute(CriarUsuarioDTO usuarioParaSalvar) {
        FilmeResponseDTO filmeResponseDTO = tmdbService.execute(usuarioParaSalvar.getFilmeFavorito());

        Usuario usuarioEntity = UsuarioDTOMapper.toEntity(usuarioParaSalvar, filmeResponseDTO);
        Usuario usuarioPersistido = UsuarioRepository.save(usuarioEntity);
        return UsuarioDTOMapper.toResponse(usuarioPersistido);
    }
}
