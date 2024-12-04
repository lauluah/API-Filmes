package com.adatech.filmes_API.service.ApiFilme;

import com.adatech.filmes_API.dto.response.ApiFilmeResponseDTO;
import com.adatech.filmes_API.repository.ApiFilmeRepository;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TMDbServiceTest {

    private static WireMockServer wireMockServer;
    private final RestTemplate restTemplate = new RestTemplate();
    private final ApiFilmeRepository mockRepository = Mockito.mock(ApiFilmeRepository.class);

    private final TMDbService tmdbService = new TMDbService(
            restTemplate,
            "mockApiKey",
            "http://localhost:8081",
            "json",
            mockRepository
    );

    @BeforeAll
    static void setupWireMock() {
        wireMockServer = new WireMockServer(WireMockConfiguration.wireMockConfig().port(8081));
        wireMockServer.start();
        WireMock.configureFor("localhost", 8081);
    }

    @AfterAll
    static void tearDownWireMock() {
        wireMockServer.stop();
    }

    @Test
    void testObterDetalhesFilme() {
        // Configurando uma resposta simulada para a API externa
        wireMockServer.stubFor(WireMock.get(WireMock.urlEqualTo("/search/movie?query=Inception&api_key=mockApiKey&format=json"))
                .willReturn(WireMock.aResponse()
                        .withStatus(200)
                        .withBody("""
                                {
                                    "results": [
                                        {
                                            "id": 123,
                                            "title": "Inception",
                                            "release_date": "2010-07-16",
                                            "overview": "A mind-bending thriller",
                                            "runtime": 148,
                                            "popularity": 9.8,
                                            "original_language": "en",
                                            "genres": [
                                                {"id": 1, "name": "Action"},
                                                {"id": 2, "name": "Science Fiction"}
                                            ]
                                        }
                                    ]
                                }
                                """)
                        .withHeader("Content-Type", "application/json")));

        // Simulação do endpoint de detalhes do filme
        wireMockServer.stubFor(WireMock.get(WireMock.urlEqualTo("/movie/123?api_key=mockApiKey&format=json"))
                .willReturn(WireMock.aResponse()
                        .withStatus(200)
                        .withBody("""
                                {
                                    "id": 123,
                                    "title": "Inception",
                                    "release_date": "2010-07-16",
                                    "overview": "A mind-bending thriller",
                                    "runtime": 148,
                                    "popularity": 9.8,
                                    "original_language": "en",
                                    "genres": [
                                        {"id": 1, "name": "Action"},
                                        {"id": 2, "name": "Science Fiction"}
                                    ]
                                }
                                """)
                        .withHeader("Content-Type", "application/json")));

        // Chamando o método a ser testado
        ApiFilmeResponseDTO response = tmdbService.obterDetalhesFilme("Inception");

        // Validações
        assertEquals("Inception", response.getTitulo());
        assertEquals("2010-07-16", response.getDataLancamento());
        assertEquals(148, response.getDuracao());
        assertEquals(9.8, response.getPopularidade());
        assertEquals("en", response.getIdiomaOriginal());
        assertEquals(2, response.getGeneros().size());
        assertEquals("Action", response.getGeneros().get(0));
    }
}
