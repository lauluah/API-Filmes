package com.adatech.filmes_API.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ApiFilmeResponseDTO {
    @JsonProperty("title")
    private String titulo;

    @JsonProperty("overview")
    private String descricao;

    @JsonProperty("release_date")
    private String dataLancamento;

    @JsonProperty("runtime")
    private int duracao;

    @JsonProperty("genres")
    private List<String> generos;

    @JsonProperty("popularity")
    private double popularidade;

    @JsonProperty("original_language")
    private String idiomaOriginal;

    public ApiFilmeResponseDTO() {
    }

    public ApiFilmeResponseDTO(String titulo, String dataLancamento, String descricao, int duracao,
                               List<String> generos, double popularidade, String idiomaOriginal) {
        this.titulo = titulo;
        this.dataLancamento = dataLancamento;
        this.descricao = descricao;
        this.duracao = duracao;
        this.generos = generos;
        this.popularidade = popularidade;
        this.idiomaOriginal = idiomaOriginal;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(String dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public List<String> getGeneros() {
        return generos;
    }

    public void setGeneros(List<String> generos) {
        this.generos = generos;
    }

    public double getPopularidade() {
        return popularidade;
    }

    public void setPopularidade(double popularidade) {
        this.popularidade = popularidade;
    }

    public String getIdiomaOriginal() {
        return idiomaOriginal;
    }

    public void setIdiomaOriginal(String idiomaOriginal) {
        this.idiomaOriginal = idiomaOriginal;
    }

    public static class Genero {
        private int id;
        private String nome;

        public Genero() {
        }

        public Genero(int id, String nome) {
            this.id = id;
            this.nome = nome;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }
    }
}