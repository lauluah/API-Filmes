package com.adatech.filmes_API.service.FilmeService;

import com.adatech.filmes_API.dto.response.FilmeApiResponseDTO;
import com.adatech.filmes_API.external.client.FilmeApiCliente;
import org.springframework.stereotype.Service;

@Service
public class TMDbService {
    private final FilmeApiCliente filmeApiCliente;

    public TMDbService(FilmeApiCliente filmeApiCliente) {
        this.filmeApiCliente = filmeApiCliente;
    }

    public FilmeApiResponseDTO obterDetalhesFilme(String movieName) {
        return filmeApiCliente.obterDetalhesFilme(movieName);
    }

    public FilmeApiResponseDTO[] obterFilmesPorGenero(String genre) {
        return filmeApiCliente.obterFilmesPorGenero(genre);
    }
}