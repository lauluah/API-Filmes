package com.adatech.filmes_API.dto.request;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;

public class CriarUsuarioDTO {

    @NotBlank(message = "O nome é obrigatório e não pode estar em branco.")
    @Size(min = 3, max = 50, message = "O nome deve ter entre 3 e 50 caracteres.")
    private String nome;

    @Positive(message = "A idade deve ser um número positivo e maior que zero.")
    private Integer idade;

    @NotBlank(message = "O email é obrigatório e não pode estar em branco.")
    @Pattern(regexp = "^[A-Za-z0-9._%+]+@[A-Za-z0-9]+(\\.[A-Za-z]{2,})+$", message = "O formato do email está inválido. Verifique se o email está correto.")
    private String email;

    @NotBlank(message = "A senha é obrigatória e não pode estar em branco.")
    @Size(min = 6, max = 12, message = "A senha deve ter entre 6 e 12 caracteres.")
    private String password;

    @NotBlank(message = "O CPF é obrigatório e não pode estar em branco.")
    @CPF(message = "O formato do CPF está inválido. Por favor, insira um CPF válido.")
    private String cpf;

    public CriarUsuarioDTO(String nome, Integer idade, String email, String password, String cpf) {
        this.nome = nome;
        this.idade = idade;
        this.email = email;
        this.password = password;
        this.cpf = cpf;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}