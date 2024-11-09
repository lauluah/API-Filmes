package com.adatech.filmes_API.dto.mapper;

import com.adatech.filmes_API.dto.request.CriarUsuarioDTO;
import com.adatech.filmes_API.dto.response.UsuarioResponseDTO;
import com.adatech.filmes_API.model.Usuario;

public class UsuarioDTOMapper {

    public static Usuario toEntity(CriarUsuarioDTO criarUsuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setNome(criarUsuarioDTO.getNome());
        usuario.setEmail(criarUsuarioDTO.getEmail());
        usuario.setCpf(criarUsuarioDTO.getCpf());
        usuario.setIdade(criarUsuarioDTO.getIdade());
        usuario.setPassword(criarUsuarioDTO.getPassword());
        //usuario.setFilmeBuscado(ApiFilmeDTOMapper.toEntity(apiFilmeResponseDTO));
        return usuario;
    }

    public static UsuarioResponseDTO toResponse(Usuario usuario) {
        UsuarioResponseDTO dto = new UsuarioResponseDTO();
        dto.setNome(usuario.getNome());
        dto.setIdade(usuario.getIdade());
        dto.setEmail(usuario.getEmail());
        return dto;
    }
}
