package com.adatech.filmes_API.service.FilmeService;

import com.adatech.filmes_API.exception.FilmeNaoEncontradoException;
import com.adatech.filmes_API.model.Filme;
import com.adatech.filmes_API.repository.FilmeRepository;
import org.springframework.stereotype.Service;

@Service
public class ObterFilmePorIDService {
    private final FilmeRepository filmeRepository;

    public ObterFilmePorIDService(FilmeRepository filmeRepository) {
        this.filmeRepository = filmeRepository;
    }

    public Filme execute(Long id) {
        return filmeRepository.findById(id).orElseThrow(() -> new FilmeNaoEncontradoException(String.format("Não foi possível encontrar um filme com id %s", id)));
    }
}
