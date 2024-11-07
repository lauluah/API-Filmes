package com.adatech.filmes_API.dto.request;

import com.adatech.filmes_API.dto.response.FilmeResponseDTO;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;

public class CriarUsuarioDTO {
    @NotBlank(message = "O nome é obrigatório e não pode estar em branco.")
    @Size(min = 3, max = 50, message = "O nome deve ter entre 3 e 50 caracteres.")
    private String nome;

    @Positive(message = "A idade deve ser um número positivo e maior que zero.")
    private Integer idade;

    @Size(min = 2, max = 40, message = "O filme deve ter entre 2 e 40 caracteres.")
    private String filmeFavorito;

    @NotBlank(message = "O email é obrigatório e não pode estar em branco.")
    @Pattern(regexp = "^[A-Za-z0-9._%+]+@[A-Za-z0-9]+(\\.[A-Za-z]{2,})+$", message = "O formato do email está inválido. Verifique se o email está correto.")
    private String email;

    @NotBlank(message = "O CPF é obrigatório e não pode estar em branco.")
    @CPF(message = "O formato do CPF está inválido. Por favor, insira um CPF válido.")
    private String cpf;

    private FilmeResponseDTO filme;

    public CriarUsuarioDTO(String nome, Integer idade, String filmeFavorito, String email, String cpf, FilmeResponseDTO filme) {
        this.nome = nome;
        this.idade = idade;
        this.filmeFavorito = filmeFavorito;
        this.email = email;
        this.cpf = cpf;
        this.filme = filme;
    }

    public @NotBlank(message = "O nome é obrigatório e não pode estar em branco.") @Size(min = 3, max = 50, message = "O nome deve ter entre 3 e 50 caracteres.") String getNome() {
        return nome;
    }

    public void setNome(@NotBlank(message = "O nome é obrigatório e não pode estar em branco.") @Size(min = 3, max = 50, message = "O nome deve ter entre 3 e 50 caracteres.") String nome) {
        this.nome = nome;
    }

    public @Positive(message = "A idade deve ser um número positivo e maior que zero.") Integer getIdade() {
        return idade;
    }

    public void setIdade(@Positive(message = "A idade deve ser um número positivo e maior que zero.") Integer idade) {
        this.idade = idade;
    }

    public @Size(min = 2, max = 40, message = "O filme deve ter entre 2 e 40 caracteres.") String getFilmeFavorito() {
        return filmeFavorito;
    }

    public void setFilmeFavorito(@Size(min = 2, max = 40, message = "O filme deve ter entre 2 e 40 caracteres.") String filmeFavorito) {
        this.filmeFavorito = filmeFavorito;
    }

    public @NotBlank(message = "O email é obrigatório e não pode estar em branco.") @Pattern(regexp = "^[A-Za-z0-9._%+]+@[A-Za-z0-9]+(\\.[A-Za-z]{2,})+$", message = "O formato do email está inválido. Verifique se o email está correto.") String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "O email é obrigatório e não pode estar em branco.") @Pattern(regexp = "^[A-Za-z0-9._%+]+@[A-Za-z0-9]+(\\.[A-Za-z]{2,})+$", message = "O formato do email está inválido. Verifique se o email está correto.") String email) {
        this.email = email;
    }

    public @NotBlank(message = "O CPF é obrigatório e não pode estar em branco.") @CPF(message = "O formato do CPF está inválido. Por favor, insira um CPF válido.") String getCpf() {
        return cpf;
    }

    public void setCpf(@NotBlank(message = "O CPF é obrigatório e não pode estar em branco.") @CPF(message = "O formato do CPF está inválido. Por favor, insira um CPF válido.") String cpf) {
        this.cpf = cpf;
    }

    public FilmeResponseDTO getFilme() {
        return filme;
    }

    public void setFilme(FilmeResponseDTO filme) {
        this.filme = filme;
    }

}
