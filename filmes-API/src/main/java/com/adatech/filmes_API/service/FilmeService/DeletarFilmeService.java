package com.adatech.filmes_API.service.FilmeService;

import com.adatech.filmes_API.exception.FilmeNaoEncontradoException;
import com.adatech.filmes_API.repository.FilmeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DeletarFilmeService {
    private final FilmeRepository filmeRepository;

    public DeletarFilmeService(FilmeRepository filmeRepository) {
        this.filmeRepository = filmeRepository;
    }

    public ResponseEntity<String> deletarFilme(Long id) {
        if (filmeRepository.existsById(id)) {
            filmeRepository.deleteById(id);
            return ResponseEntity.ok("Filme deletado com sucesso.");
        }
        throw new FilmeNaoEncontradoException("Filme não encontrado.");
    }
}
