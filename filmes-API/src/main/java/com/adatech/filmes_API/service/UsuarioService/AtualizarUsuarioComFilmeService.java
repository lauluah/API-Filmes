package com.adatech.filmes_API.service.UsuarioService;

import com.adatech.filmes_API.dto.mapper.ApiFilmeDTOMapper;
import com.adatech.filmes_API.dto.response.ApiFilmeResponseDTO;
import com.adatech.filmes_API.model.ApiFilme;
import com.adatech.filmes_API.model.Usuario;
import com.adatech.filmes_API.repository.UsuarioRepository;
import com.adatech.filmes_API.service.ApiFilme.TMDbService;
import org.springframework.stereotype.Service;

@Service
public class AtualizarUsuarioComFilmeService {
    private final UsuarioRepository usuarioRepository;
    private final TMDbService tmdbService;

    public AtualizarUsuarioComFilmeService(UsuarioRepository usuarioRepository, TMDbService tmdbService) {
        this.usuarioRepository = usuarioRepository;
        this.tmdbService = tmdbService;
    }

    public Usuario atualizarUsuarioComFilme(Long usuarioId, String title) {
        Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        ApiFilmeResponseDTO apiFilmeResponseDTO = tmdbService.obterDetalhesFilme(title);
        ApiFilme apiFilmeEntity = ApiFilmeDTOMapper.toEntity(apiFilmeResponseDTO);
        usuario.setFilmeBuscado(apiFilmeEntity);
        return usuarioRepository.save(usuario);
    }
}