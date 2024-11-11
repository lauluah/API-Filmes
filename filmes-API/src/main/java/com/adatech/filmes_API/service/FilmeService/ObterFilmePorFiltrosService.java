package com.adatech.filmes_API.service.FilmeService;

import com.adatech.filmes_API.exception.FilmeNaoEncontradoException;
import com.adatech.filmes_API.model.Filme;
import com.adatech.filmes_API.repository.FilmeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ObterFilmePorFiltrosService {
    private final FilmeRepository filmeRepository;

    public ObterFilmePorFiltrosService(FilmeRepository filmeRepository) {
        this.filmeRepository = filmeRepository;
    }

    public List<Filme> obterFilmesPorGenero(String genero) {
        List<Filme> filmesPorGenero = filmeRepository.findByGeneroContaining(genero);
        if (filmesPorGenero.isEmpty()) {
            throw new FilmeNaoEncontradoException(String.format("Não foi possível encontrar filmes com o gênero: %s", genero));
        }
        return filmesPorGenero;
    }

    public List<Filme> obterFilmesPorNomeFilme(String nomeFilme) {
        List<Filme> filmesPorNome = filmeRepository.findByNomeFilmeContaining(nomeFilme);
        if (filmesPorNome.isEmpty()) {
            throw new FilmeNaoEncontradoException(String.format("Não foi possível encontrar filmes com o nome: %s", nomeFilme));
        }
        return filmesPorNome;
    }

    public List<Filme> obterFilmesPorNota(double nota) {
        List<Filme> filmesPorNota = filmeRepository.findByNota(nota);
        if (filmesPorNota.isEmpty()) {
            throw new FilmeNaoEncontradoException(String.format("Não foi possível encontrar filmes com a nota: %.2f", nota));
        }
        return filmesPorNota;
    }

    public List<Filme> obterFilmesPorCorAvaliacao(String corAvaliacao) {
        List<Filme> filmesPorCorAvaliacao = filmeRepository.findByCorAvaliacao(corAvaliacao);
        if (filmesPorCorAvaliacao.isEmpty()) {
            throw new FilmeNaoEncontradoException(String.format("Não foi possível encontrar filmes com a cor de avaliação: %s", corAvaliacao));
        }
        return filmesPorCorAvaliacao;
    }

    public Filme obterFilmePorId(Long id) {
        Optional<Filme> filme = filmeRepository.findById(id);
        if (filme.isEmpty()) {
            throw new FilmeNaoEncontradoException(String.format("Não foi possível encontrar o filme com o id: %d", id));
        }
        return filme.get();
    }
}