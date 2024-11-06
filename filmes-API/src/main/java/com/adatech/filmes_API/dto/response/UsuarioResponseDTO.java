package com.adatech.filmes_API.dto.response;

public class UsuarioResponseDTO {
    private String nome;
    private Integer idade;
    private String filmeFavorito;
    private String cpf;

    public UsuarioResponseDTO(String nome, Integer idade, String filmeFavorito, String cpf) {
        this.nome = nome;
        this.idade = idade;
        this.filmeFavorito = filmeFavorito;
        this.cpf = cpf;
    }

    public UsuarioResponseDTO() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public String getFilmeFavorito() {
        return filmeFavorito;
    }

    public void setFilmeFavorito(String filmeFavorito) {
        this.filmeFavorito = filmeFavorito;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
