package com.adatech.filmes_API;

import com.adatech.filmes_API.dto.response.ApiFilmeResponseDTO;
import com.adatech.filmes_API.service.ApiFilme.TMDbService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.Base64;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class FilmesApiApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private TMDbService tmdbService;

	@Test
	void deveRetornarDetalhesDoFilme() throws Exception {
		ApiFilmeResponseDTO mockResponse = new ApiFilmeResponseDTO(
				"Mock Title",
				"Mock Overview",
				"2024-12-01",
				120,
				Arrays.asList("Drama", "Action"),
				8.5,
				"en"
		);

		// Mockando o serviço
		when(tmdbService.obterDetalhesFilme("Mock_Title")).thenReturn(mockResponse);

		// Testando o endpoint
		mockMvc.perform(MockMvcRequestBuilders
						.get("/api/filmes/tmdb/nome/Mock_Title")
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.title").value("Mock Title"))
				.andExpect(jsonPath("$.overview").value("2024-12-01"))
				.andExpect(jsonPath("$.release_date").value("Mock Overview"))
				.andExpect(jsonPath("$.runtime").value(120))
				.andExpect(jsonPath("$.genres[0]").value("Drama"))
				.andExpect(jsonPath("$.genres[1]").value("Action"))
				.andExpect(jsonPath("$.popularity").value(8.5))
				.andExpect(jsonPath("$.original_language").value("en"));

	}
}
