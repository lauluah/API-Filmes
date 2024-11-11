package com.adatech.filmes_API.dto.response;

public class FilmeResponseDTO {
    private String nomeFilme;
    private String genero;
    private Double nota;
    private String comentario;
    private String corAvaliacao;

    public FilmeResponseDTO(String nomeFilme, String genero, Double nota, String comentario, String corAvaliacao) {
        this.nomeFilme = nomeFilme;
        this.genero = genero;
        this.nota = nota;
        this.comentario = comentario;
        this.corAvaliacao = corAvaliacao;
    }

    public FilmeResponseDTO() {

    }

    public String getNomeFilme() {
        return nomeFilme;
    }

    public void setNomeFilme(String nomeFilme) {
        this.nomeFilme = nomeFilme;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getCorAvaliacao() {
        return corAvaliacao;
    }

    public void setCorAvaliacao(String corAvaliacao) {
        this.corAvaliacao = corAvaliacao;
    }
}