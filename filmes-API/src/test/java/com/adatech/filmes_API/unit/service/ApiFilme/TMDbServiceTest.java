package com.adatech.filmes_API.service.ApiFilme;

import com.adatech.filmes_API.dto.mapper.ApiFilmeDTOMapper;
import com.adatech.filmes_API.dto.response.ApiFilmeResponseDTO;
import com.adatech.filmes_API.model.ApiFilme;
import com.adatech.filmes_API.repository.ApiFilmeRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.*;

public class TMDbServiceTest {

    private final RestTemplate restTemplate = new RestTemplate();
    private final ApiFilmeRepository mockRepository = Mockito.mock(ApiFilmeRepository.class);

    private final TMDbService tmdbService = new TMDbService(
            restTemplate,
            "mockApiKey",
            "http://localhost:8081",
            "json",
            mockRepository
    );

    @Test
    void testObterDetalhesFilme() {
        MockRestServiceServer mockServer = MockRestServiceServer.createServer(restTemplate);

        mockServer.expect(requestTo("http://localhost:8081/search/movie?query=Inception&api_key=mockApiKey&format=json"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess("""
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
                                """, MediaType.APPLICATION_JSON));

        mockServer.expect(requestTo("http://localhost:8081/movie/123?api_key=mockApiKey&format=json"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess("""
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
                                """, MediaType.APPLICATION_JSON));

        ApiFilmeResponseDTO response = tmdbService.obterDetalhesFilme("Inception");

        assertEquals("Inception", response.getTitulo());
        assertEquals("2010-07-16", response.getDataLancamento());
        assertEquals(148, response.getDuracao());
        assertEquals(9.8, response.getPopularidade());
        assertEquals("en", response.getIdiomaOriginal());
        assertEquals(2, response.getGeneros().size());
        assertEquals("Action", response.getGeneros().get(0));

        mockServer.verify();
    }

    @Test
    void testFilmeSemGenero() {
        MockRestServiceServer mockServer = MockRestServiceServer.createServer(restTemplate);

        mockServer.expect(requestTo("http://localhost:8081/search/movie?query=Genreless&api_key=mockApiKey&format=json"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess("""
                                {
                                    "results": [
                                        {
                                            "id": 456,
                                            "title": "Genreless Movie",
                                            "release_date": "2023-01-01",
                                            "overview": "A movie with no genre",
                                            "runtime": 120,
                                            "popularity": 5.0,
                                            "original_language": "en",
                                            "genres": []
                                        }
                                    ]
                                }
                                """, MediaType.APPLICATION_JSON));

        mockServer.expect(requestTo("http://localhost:8081/movie/456?api_key=mockApiKey&format=json"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess("""
                                {
                                    "id": 456,
                                    "title": "Genreless Movie",
                                    "release_date": "2023-01-01",
                                    "overview": "A movie with no genre",
                                    "runtime": 120,
                                    "popularity": 5.0,
                                    "original_language": "en",
                                    "genres": []
                                }
                                """, MediaType.APPLICATION_JSON));

        ApiFilmeResponseDTO response = tmdbService.obterDetalhesFilme("Genreless");

        assertEquals("Genreless Movie", response.getTitulo());
        assertEquals("2023-01-01", response.getDataLancamento());
        assertEquals(120, response.getDuracao());
        assertEquals(5.0, response.getPopularidade());
        assertEquals("en", response.getIdiomaOriginal());
        assertNotNull(response.getGeneros());
        assertTrue(response.getGeneros().isEmpty());

        mockServer.verify();
    }

    @Test
    void testFilmeComMuitosGeneros() {
        MockRestServiceServer mockServer = MockRestServiceServer.createServer(restTemplate);

        mockServer.expect(requestTo("http://localhost:8081/search/movie?query=Epic&api_key=mockApiKey&format=json"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess("""
                                {
                                    "results": [
                                        {
                                            "id": 789,
                                            "title": "Epic Movie",
                                            "release_date": "2022-12-25",
                                            "overview": "An epic movie with many genres",
                                            "runtime": 180,
                                            "popularity": 8.5,
                                            "original_language": "en",
                                            "genres": [
                                                {"id": 1, "name": "Action"},
                                                {"id": 2, "name": "Adventure"},
                                                {"id": 3, "name": "Drama"},
                                                {"id": 4, "name": "Fantasy"}
                                            ]
                                        }
                                    ]
                                }
                                """, MediaType.APPLICATION_JSON));

        mockServer.expect(requestTo("http://localhost:8081/movie/789?api_key=mockApiKey&format=json"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess("""
                                {
                                    "id": 789,
                                    "title": "Epic Movie",
                                    "release_date": "2022-12-25",
                                    "overview": "An epic movie with many genres",
                                    "runtime": 180,
                                    "popularity": 8.5,
                                    "original_language": "en",
                                    "genres": [
                                        {"id": 1, "name": "Action"},
                                        {"id": 2, "name": "Adventure"},
                                        {"id": 3, "name": "Drama"},
                                        {"id": 4, "name": "Fantasy"}
                                    ]
                                }
                                """, MediaType.APPLICATION_JSON));

        ApiFilmeResponseDTO response = tmdbService.obterDetalhesFilme("Epic");

        assertEquals("Epic Movie", response.getTitulo());
        assertEquals("2022-12-25", response.getDataLancamento());
        assertEquals(180, response.getDuracao());
        assertEquals(8.5, response.getPopularidade());
        assertEquals("en", response.getIdiomaOriginal());
        assertEquals(4, response.getGeneros().size());
        assertEquals("Action", response.getGeneros().get(0));
        assertEquals("Adventure", response.getGeneros().get(1));

        mockServer.verify();
    }
    @Test
    void testFilmeSemDataDeLancamento() {
        MockRestServiceServer mockServer = MockRestServiceServer.createServer(restTemplate);

        mockServer.expect(requestTo("http://localhost:8081/search/movie?query=NoReleaseDate&api_key=mockApiKey&format=json"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess("""
                            {
                                "results": [
                                    {
                                        "id": 987,
                                        "title": "No Release Date",
                                        "overview": "A movie with no release date",
                                        "runtime": 100,
                                        "popularity": 7.5,
                                        "original_language": "en",
                                        "genres": [
                                            {"id": 1, "name": "Mystery"}
                                        ]
                                    }
                                ]
                            }
                            """, MediaType.APPLICATION_JSON));

        mockServer.expect(requestTo("http://localhost:8081/movie/987?api_key=mockApiKey&format=json"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess("""
                            {
                                "id": 987,
                                "title": "No Release Date",
                                "overview": "A movie with no release date",
                                "runtime": 100,
                                "popularity": 7.5,
                                "original_language": "en",
                                "genres": [
                                    {"id": 1, "name": "Mystery"}
                                ]
                            }
                            """, MediaType.APPLICATION_JSON));

        ApiFilmeResponseDTO response = tmdbService.obterDetalhesFilme("NoReleaseDate");

        assertEquals("No Release Date", response.getTitulo());
        assertNull(response.getDataLancamento()); // Validando que a data de lançamento é nula
        assertEquals(100, response.getDuracao());
        assertEquals(7.5, response.getPopularidade());
        assertEquals("en", response.getIdiomaOriginal());
        assertEquals(1, response.getGeneros().size());
        assertEquals("Mystery", response.getGeneros().get(0));

        mockServer.verify();
    }
    @Test
    void testFilmeComPopularidadeZero() {
        MockRestServiceServer mockServer = MockRestServiceServer.createServer(restTemplate);

        mockServer.expect(requestTo("http://localhost:8081/search/movie?query=ZeroPopularity&api_key=mockApiKey&format=json"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess("""
                            {
                                "results": [
                                    {
                                        "id": 654,
                                        "title": "Zero Popularity",
                                        "release_date": "2021-05-10",
                                        "overview": "A movie with no popularity",
                                        "runtime": 90,
                                        "popularity": 0.0,
                                        "original_language": "en",
                                        "genres": [
                                            {"id": 1, "name": "Comedy"}
                                        ]
                                    }
                                ]
                            }
                            """, MediaType.APPLICATION_JSON));

        mockServer.expect(requestTo("http://localhost:8081/movie/654?api_key=mockApiKey&format=json"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess("""
                            {
                                "id": 654,
                                "title": "Zero Popularity",
                                "release_date": "2021-05-10",
                                "overview": "A movie with no popularity",
                                "runtime": 90,
                                "popularity": 0.0,
                                "original_language": "en",
                                "genres": [
                                    {"id": 1, "name": "Comedy"}
                                ]
                            }
                            """, MediaType.APPLICATION_JSON));

        ApiFilmeResponseDTO response = tmdbService.obterDetalhesFilme("ZeroPopularity");

        assertEquals("Zero Popularity", response.getTitulo());
        assertEquals("2021-05-10", response.getDataLancamento());
        assertEquals(90, response.getDuracao());
        assertEquals(0.0, response.getPopularidade());
        assertEquals("en", response.getIdiomaOriginal());
        assertEquals(1, response.getGeneros().size());
        assertEquals("Comedy", response.getGeneros().get(0));

        mockServer.verify();
    }
    @Test
    void testFilmeComIdiomaDesconhecido() {
        MockRestServiceServer mockServer = MockRestServiceServer.createServer(restTemplate);

        mockServer.expect(requestTo("http://localhost:8081/search/movie?query=UnknownLanguage&api_key=mockApiKey&format=json"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess("""
                            {
                                "results": [
                                    {
                                        "id": 321,
                                        "title": "Unknown Language",
                                        "release_date": "2020-11-15",
                                        "overview": "A movie with an unknown language",
                                        "runtime": 110,
                                        "popularity": 6.0,
                                        "original_language": null,
                                        "genres": [
                                            {"id": 1, "name": "Thriller"}
                                        ]
                                    }
                                ]
                            }
                            """, MediaType.APPLICATION_JSON));

        mockServer.expect(requestTo("http://localhost:8081/movie/321?api_key=mockApiKey&format=json"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess("""
                            {
                                "id": 321,
                                "title": "Unknown Language",
                                "release_date": "2020-11-15",
                                "overview": "A movie with an unknown language",
                                "runtime": 110,
                                "popularity": 6.0,
                                "original_language": null,
                                "genres": [
                                    {"id": 1, "name": "Thriller"}
                                ]
                            }
                            """, MediaType.APPLICATION_JSON));

        ApiFilmeResponseDTO response = tmdbService.obterDetalhesFilme("UnknownLanguage");

        assertEquals("Unknown Language", response.getTitulo());
        assertEquals("2020-11-15", response.getDataLancamento());
        assertEquals(110, response.getDuracao());
        assertEquals(6.0, response.getPopularidade());
        assertNull(response.getIdiomaOriginal());
        assertEquals(1, response.getGeneros().size());
        assertEquals("Thriller", response.getGeneros().get(0));

        mockServer.verify();
    }
    @Test
    void testFilmeSemTitulo() {
        MockRestServiceServer mockServer = MockRestServiceServer.createServer(restTemplate);

        mockServer.expect(requestTo("http://localhost:8081/search/movie?query=NoTitle&api_key=mockApiKey&format=json"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess("""
                            {
                                "results": [
                                    {
                                        "id": 987,
                                        "title": null,
                                        "release_date": "2019-08-20",
                                        "overview": "A movie with no title",
                                        "runtime": 95,
                                        "popularity": 7.5,
                                        "original_language": "en",
                                        "genres": [
                                            {"id": 1, "name": "Drama"}
                                        ]
                                    }
                                ]
                            }
                            """, MediaType.APPLICATION_JSON));

        mockServer.expect(requestTo("http://localhost:8081/movie/987?api_key=mockApiKey&format=json"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess("""
                            {
                                "id": 987,
                                "title": null,
                                "release_date": "2019-08-20",
                                "overview": "A movie with no title",
                                "runtime": 95,
                                "popularity": 7.5,
                                "original_language": "en",
                                "genres": [
                                    {"id": 1, "name": "Drama"}
                                ]
                            }
                            """, MediaType.APPLICATION_JSON));

        ApiFilmeResponseDTO response = tmdbService.obterDetalhesFilme("NoTitle");

        assertNull(response.getTitulo());
        assertEquals("2019-08-20", response.getDataLancamento());
        assertEquals(95, response.getDuracao());
        assertEquals(7.5, response.getPopularidade());
        assertEquals("en", response.getIdiomaOriginal());
        assertEquals(1, response.getGeneros().size());
        assertEquals("Drama", response.getGeneros().get(0));

        mockServer.verify();
    }
    @Test
    void testFilmeComDataInvalida() {
        MockRestServiceServer mockServer = MockRestServiceServer.createServer(restTemplate);

        mockServer.expect(requestTo("http://localhost:8081/search/movie?query=InvalidDate&api_key=mockApiKey&format=json"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess("""
                            {
                                "results": [
                                    {
                                        "id": 112,
                                        "title": "Invalid Date Movie",
                                        "release_date": null,
                                        "overview": "A movie with an invalid release date",
                                        "runtime": 100,
                                        "popularity": 6.0,
                                        "original_language": "en",
                                        "genres": [
                                            {"id": 1, "name": "Comedy"}
                                        ]
                                    }
                                ]
                            }
                            """, MediaType.APPLICATION_JSON));

        mockServer.expect(requestTo("http://localhost:8081/movie/112?api_key=mockApiKey&format=json"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess("""
                            {
                                "id": 112,
                                "title": "Invalid Date Movie",
                                "release_date": null,
                                "overview": "A movie with an invalid release date",
                                "runtime": 100,
                                "popularity": 6.0,
                                "original_language": "en",
                                "genres": [
                                    {"id": 1, "name": "Comedy"}
                                ]
                            }
                            """, MediaType.APPLICATION_JSON));

        ApiFilmeResponseDTO response = tmdbService.obterDetalhesFilme("InvalidDate");

        assertEquals("Invalid Date Movie", response.getTitulo());
        assertNull(response.getDataLancamento());
        assertEquals(100, response.getDuracao());
        assertEquals(6.0, response.getPopularidade());
        assertEquals("en", response.getIdiomaOriginal());
        assertEquals(1, response.getGeneros().size());
        assertEquals("Comedy", response.getGeneros().get(0));

        mockServer.verify();
    }
    @Test
    void testFilmeComDuracaoZero() {
        MockRestServiceServer mockServer = MockRestServiceServer.createServer(restTemplate);

        mockServer.expect(requestTo("http://localhost:8081/search/movie?query=ZeroDuration&api_key=mockApiKey&format=json"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess("""
                            {
                                "results": [
                                    {
                                        "id": 223,
                                        "title": "Zero Duration Movie",
                                        "release_date": "2021-03-10",
                                        "overview": "A movie with zero duration",
                                        "runtime": 0,
                                        "popularity": 4.5,
                                        "original_language": "en",
                                        "genres": [
                                            {"id": 1, "name": "Horror"}
                                        ]
                                    }
                                ]
                            }
                            """, MediaType.APPLICATION_JSON));

        mockServer.expect(requestTo("http://localhost:8081/movie/223?api_key=mockApiKey&format=json"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess("""
                            {
                                "id": 223,
                                "title": "Zero Duration Movie",
                                "release_date": "2021-03-10",
                                "overview": "A movie with zero duration",
                                "runtime": 0,
                                "popularity": 4.5,
                                "original_language": "en",
                                "genres": [
                                    {"id": 1, "name": "Horror"}
                                ]
                            }
                            """, MediaType.APPLICATION_JSON));

        ApiFilmeResponseDTO response = tmdbService.obterDetalhesFilme("ZeroDuration");

        assertEquals("Zero Duration Movie", response.getTitulo());
        assertEquals("2021-03-10", response.getDataLancamento());
        assertEquals(0, response.getDuracao());
        assertEquals(4.5, response.getPopularidade());
        assertEquals("en", response.getIdiomaOriginal());
        assertEquals(1, response.getGeneros().size());
        assertEquals("Horror", response.getGeneros().get(0));

        mockServer.verify();
    }
    @Test
    void testRespostaVazia() {
        MockRestServiceServer mockServer = MockRestServiceServer.createServer(restTemplate);

        mockServer.expect(requestTo("http://localhost:8081/search/movie?query=EmptyResponse&api_key=mockApiKey&format=json"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess("", MediaType.APPLICATION_JSON));

        ApiFilmeResponseDTO response = tmdbService.obterDetalhesFilme("EmptyResponse");

        assertNull(response);

        mockServer.verify();
    }
    @Test
    void testMapperToEntityAndToDTO() {
        ApiFilmeResponseDTO responseDTO = new ApiFilmeResponseDTO();
        responseDTO.setTitulo("Interstellar");
        responseDTO.setDescricao("A journey beyond the stars");
        responseDTO.setDataLancamento("2014-11-07");
        responseDTO.setDuracao(169);
        responseDTO.setGeneros(Arrays.asList("Adventure", "Drama", "Sci-Fi"));
        responseDTO.setPopularidade(9.1);
        responseDTO.setIdiomaOriginal("en");

        ApiFilme entity = ApiFilmeDTOMapper.toEntity(responseDTO);
        assertEquals("Interstellar", entity.getTitle());
        assertEquals("A journey beyond the stars", entity.getOverview());
        assertEquals("2014-11-07", entity.getReleaseDate());
        assertEquals(169, entity.getRuntime());
        assertEquals("Adventure, Drama, Sci-Fi", entity.getGenres());
        assertEquals(9.1, entity.getPopularity());
        assertEquals("en", entity.getOriginalLanguage());

        ApiFilmeResponseDTO mappedDTO = ApiFilmeDTOMapper.toDTO(entity);
        assertEquals(responseDTO.getTitulo(), mappedDTO.getTitulo());
        assertEquals(responseDTO.getDescricao(), mappedDTO.getDescricao());
        assertEquals(responseDTO.getDataLancamento(), mappedDTO.getDataLancamento());
        assertEquals(responseDTO.getDuracao(), mappedDTO.getDuracao());
        assertEquals(responseDTO.getGeneros(), mappedDTO.getGeneros());
        assertEquals(responseDTO.getPopularidade(), mappedDTO.getPopularidade());
        assertEquals(responseDTO.getIdiomaOriginal(), mappedDTO.getIdiomaOriginal());
    }
    @Test
    void testSalvarDetalhesFilme() {
        MockRestServiceServer mockServer = MockRestServiceServer.createServer(restTemplate);

        mockServer.expect(requestTo("http://localhost:8081/search/movie?query=Interstellar&api_key=mockApiKey&format=json"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess("""
                    {
                        "results": [
                            {
                                "id": 123,
                                "title": "Interstellar",
                                "release_date": "2014-11-07",
                                "overview": "A journey beyond the stars",
                                "runtime": 169,
                                "popularity": 9.1,
                                "original_language": "en",
                                "genres": [
                                    {"id": 1, "name": "Adventure"},
                                    {"id": 2, "name": "Drama"},
                                    {"id": 3, "name": "Sci-Fi"}
                                ]
                            }
                        ]
                    }
                    """, MediaType.APPLICATION_JSON));

        mockServer.expect(requestTo("http://localhost:8081/movie/123?api_key=mockApiKey&format=json"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess("""
                    {
                        "id": 123,
                        "title": "Interstellar",
                        "release_date": "2014-11-07",
                        "overview": "A journey beyond the stars",
                        "runtime": 169,
                        "popularity": 9.1,
                        "original_language": "en",
                        "genres": [
                            {"id": 1, "name": "Adventure"},
                            {"id": 2, "name": "Drama"},
                            {"id": 3, "name": "Sci-Fi"}
                        ]
                    }
                    """, MediaType.APPLICATION_JSON));

        ApiFilme mockApiFilme = new ApiFilme();
        mockApiFilme.setTitle("Interstellar");
        mockApiFilme.setOverview("A journey beyond the stars");
        mockApiFilme.setReleaseDate("2014-11-07");
        Mockito.when(mockRepository.save(Mockito.any(ApiFilme.class))).thenReturn(mockApiFilme);

        ApiFilme savedFilme = tmdbService.salvarDetalhesFilme("Interstellar");

        assertNotNull(savedFilme);
        assertEquals("Interstellar", savedFilme.getTitle());
        assertEquals("A journey beyond the stars", savedFilme.getOverview());
        assertEquals("2014-11-07", savedFilme.getReleaseDate());

        Mockito.verify(mockRepository, Mockito.times(1)).save(Mockito.any(ApiFilme.class));
        mockServer.verify();
    }

    @Test
    void testControllerObterDetalhesFilme() {
        MockRestServiceServer mockServer = MockRestServiceServer.createServer(restTemplate);

        mockServer.expect(requestTo("http://localhost:8081/search/movie?query=Interstellar&api_key=mockApiKey&format=json"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess("""
                    {
                        "results": [
                            {
                                "id": 999,
                                "title": "Interstellar",
                                "release_date": "2014-11-07",
                                "overview": "A journey beyond the stars",
                                "runtime": 169,
                                "popularity": 9.1,
                                "original_language": "en",
                                "genres": [
                                    {"id": 1, "name": "Adventure"},
                                    {"id": 2, "name": "Drama"},
                                    {"id": 3, "name": "Sci-Fi"}
                                ]
                            }
                        ]
                    }
                    """, MediaType.APPLICATION_JSON));

        mockServer.expect(requestTo("http://localhost:8081/movie/999?api_key=mockApiKey&format=json"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess("""
                    {
                        "id": 999,
                        "title": "Interstellar",
                        "release_date": "2014-11-07",
                        "overview": "A journey beyond the stars",
                        "runtime": 169,
                        "popularity": 9.1,
                        "original_language": "en",
                        "genres": [
                            {"id": 1, "name": "Adventure"},
                            {"id": 2, "name": "Drama"},
                            {"id": 3, "name": "Sci-Fi"}
                        ]
                    }
                    """, MediaType.APPLICATION_JSON));


        ApiFilmeResponseDTO response = tmdbService.obterDetalhesFilme("Interstellar");

        // Validações
        assertNotNull(response);
        assertEquals("Interstellar", response.getTitulo());
        assertEquals("2014-11-07", response.getDataLancamento());
        assertEquals(169, response.getDuracao());
        assertEquals(9.1, response.getPopularidade());
        assertEquals("en", response.getIdiomaOriginal());
        assertEquals(3, response.getGeneros().size());
        assertEquals("Adventure", response.getGeneros().get(0));
        assertEquals("Drama", response.getGeneros().get(1));
        assertEquals("Sci-Fi", response.getGeneros().get(2));

        mockServer.verify();
    }


}
