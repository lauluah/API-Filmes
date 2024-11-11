package com.adatech.filmes_API.model;

import jakarta.persistence.*;

@Entity
@Table(name = "FILME")
public class Filme {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "NOME_FILME", nullable = false)
    private String nomeFilme;

    @Column(name = "GENERO", nullable = false)
    private String genero;

    @Column(name = "NOTA", nullable = false)
    private Double nota;

    @Column(name = "COMENTARIO", nullable = false)
    private String comentario;

    @Column(name = "COR_AVALIACAO", nullable = false)
    private String corAvaliacao;

    public Filme(Long id, String nomeFilme, String genero, double nota, String comentario, String corAvaliacao) {
        this.id = id;
        this.nomeFilme = nomeFilme;
        this.genero = genero;
        this.nota = nota;
        this.comentario = comentario;
        this.corAvaliacao = corAvaliacao;
    }

    public Filme() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
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
