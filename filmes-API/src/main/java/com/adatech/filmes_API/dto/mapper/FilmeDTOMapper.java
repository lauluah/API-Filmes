package com.adatech.filmes_API.dto.mapper;

import com.adatech.filmes_API.dto.request.CriarFilmeDTO;
import com.adatech.filmes_API.dto.response.FilmeResponseDTO;
import com.adatech.filmes_API.model.Filme;

public class FilmeDTOMapper {

    public static Filme toEntity(CriarFilmeDTO criarFilmeDTO) {
        Filme filme = new Filme();
        filme.setNomeFilme(criarFilmeDTO.getNomeFilme());
        filme.setGenero(criarFilmeDTO.getGenero());
        filme.setNota(criarFilmeDTO.getNota());
        filme.setComentario(criarFilmeDTO.getComentario());
        filme.setCorAvaliacao(criarFilmeDTO.getCorAvaliacao());
        return filme;
    }

    public static FilmeResponseDTO toResponse(Filme filme) {
        FilmeResponseDTO dto = new FilmeResponseDTO();
        dto.setNomeFilme(filme.getNomeFilme());
        dto.setGenero(filme.getGenero());
        dto.setNota(filme.getNota());
        dto.setComentario(filme.getComentario());
        dto.setCorAvaliacao(filme.getCorAvaliacao());
        return dto;
    }
}
