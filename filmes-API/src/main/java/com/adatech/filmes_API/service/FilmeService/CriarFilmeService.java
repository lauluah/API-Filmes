package com.adatech.filmes_API.service.FilmeService;

import com.adatech.filmes_API.dto.mapper.FilmeDTOMapper;
import com.adatech.filmes_API.dto.mapper.UsuarioDTOMapper;
import com.adatech.filmes_API.dto.request.CriarFilmeDTO;
import com.adatech.filmes_API.dto.response.FilmeResponseDTO;
import com.adatech.filmes_API.model.Filme;
import com.adatech.filmes_API.model.Usuario;
import com.adatech.filmes_API.repository.FilmeRepository;
import org.springframework.stereotype.Service;

@Service
public class CriarFilmeService {
    private final FilmeRepository filmeRepository;

    public CriarFilmeService(FilmeRepository filmeRepository) {
        this.filmeRepository = filmeRepository;
    }

    public FilmeResponseDTO execute(CriarFilmeDTO filmeParaSalvar) {
        Filme filmeEntity = FilmeDTOMapper.toEntity(filmeParaSalvar);
        Filme filmeSalvo = filmeRepository.save(filmeEntity);
        return FilmeDTOMapper.toResponse(filmeSalvo);
    }
}
