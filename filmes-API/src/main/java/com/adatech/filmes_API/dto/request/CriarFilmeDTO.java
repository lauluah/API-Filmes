package com.adatech.filmes_API.dto.request;

import jakarta.validation.constraints.*;

public class CriarFilmeDTO {

    @NotBlank(message = "O nome do filme é obrigatório e não pode estar em branco.")
    @Size(min = 3, max = 50, message = "O nome do filme deve ter entre 3 e 50 caracteres.")
    private String nomeFilme;

    @NotBlank(message = "O genero do filme é obrigatório e não pode estar em branco.")
    @Size(min = 3, max = 10, message = "O gênero do filme deve ter entre 3 e 10 caracteres.")
    private String genero;

    @Min(value = 0, message = "A nota deve ser igual ou maior que 0.")
    @Max(value = 10, message = "A nota deve ser igual ou menor que 10.")
    private Double nota;

    @Size(min = 3, max = 60, message = "O comentário do filme deve ter entre 3 e 60 caracteres.")
    private String comentario;

    @NotBlank(message = "A cor da avaliação é obrigatória e não pode estar em branco.")
    @Pattern(regexp = "(?i)vermelho|amarelo|azul|verde", message = "A cor deve ser uma das seguintes: vermelho, amarelo, azul, verde.")
    private String corAvaliacao;

    public CriarFilmeDTO(String nomeFilme, String genero, Double nota, String comentario, String corAvaliacao) {
        this.nomeFilme = nomeFilme;
        this.genero = genero;
        this.nota = nota;
        this.comentario = comentario;
        this.corAvaliacao = corAvaliacao;
    }

    public CriarFilmeDTO() {

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