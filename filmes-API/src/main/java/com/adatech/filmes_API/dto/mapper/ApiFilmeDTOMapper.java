package com.adatech.filmes_API.dto.mapper;

import com.adatech.filmes_API.dto.response.ApiFilmeResponseDTO;
import com.adatech.filmes_API.model.ApiFilme;

import java.util.Arrays;

public class ApiFilmeDTOMapper {

    public static ApiFilme toEntity(ApiFilmeResponseDTO dto) {
        ApiFilme apiFilme = new ApiFilme();
        apiFilme.setTitle(dto.getTitulo());
        apiFilme.setOverview(dto.getDescricao());
        apiFilme.setReleaseDate(dto.getDataLancamento());
        apiFilme.setRuntime(dto.getDuracao());
        apiFilme.setGenres(String.join(", ", dto.getGeneros()));
        apiFilme.setPopularity(dto.getPopularidade());
        apiFilme.setOriginalLanguage(dto.getIdiomaOriginal());
        return apiFilme;
    }

    public static ApiFilmeResponseDTO toDTO(ApiFilme entity) {
        ApiFilmeResponseDTO dto = new ApiFilmeResponseDTO();
        dto.setTitulo(entity.getTitle());
        dto.setDescricao(entity.getOverview());
        dto.setDataLancamento(entity.getReleaseDate());
        dto.setDuracao(entity.getRuntime());
        dto.setGeneros(Arrays.asList(entity.getGenres().split(", ")));
        dto.setPopularidade(entity.getPopularity());
        dto.setIdiomaOriginal(entity.getOriginalLanguage());
        return dto;
    }
}