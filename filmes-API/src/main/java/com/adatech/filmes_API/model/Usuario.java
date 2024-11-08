package com.adatech.filmes_API.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "USUARIO")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "NOME", nullable = false)
    private String nome;

    @Column(name = "IDADE", nullable = false)
    private Integer idade;

    @Column(name = "FILME_FAV", nullable = false)
    private String filmeFavorito;

    @Column(name = "CPF", nullable = false, unique = true)
    private String cpf;

    @Column(name = "EMAIL", nullable = false, unique = true)
    private String email;

    @Column(name = "DATA_HORA_CRIACAO")
    private LocalDateTime dataHoraCriacao;

    //Relacionamento com a tabela ApiFilme
    //Cada usuário pode ser vinculado a um filme, enquanto um único filme pode ser vinculado a vários usuários
    @ManyToOne(cascade = CascadeType.PERSIST)
    //Nome da coluna que será criada na tabela USUARIO
    //A coluna FILME_BUSCADO_ID irá armazenar o id que foi gerado dentro da tabela FILME_API_RESPONSE
    @JoinColumn(name = "FILME_BUSCADO_ID")
    private ApiFilme filmeBuscado;

    public Usuario() {
        this.dataHoraCriacao = LocalDateTime.now();
    }

    public Usuario(Long id, String nome, Integer idade, String filmeFavorito, String cpf, String email, ApiFilme filmeBuscado) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.filmeFavorito = filmeFavorito;
        this.cpf = cpf;
        this.email = email;
        this.filmeBuscado = filmeBuscado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDateTime getDataHoraCriacao() {
        return dataHoraCriacao;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public String getFilmeFavorito() {
        return filmeFavorito;
    }

    public void setFilmeFavorito(String filmeFavorito) {
        this.filmeFavorito = filmeFavorito;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDataHoraCriacao(LocalDateTime dataHoraCriacao) {
        this.dataHoraCriacao = dataHoraCriacao;
    }

    public ApiFilme getFilmeBuscado() {
        return filmeBuscado;
    }

    public void setFilmeBuscado(ApiFilme filmeBuscado) {
        this.filmeBuscado = filmeBuscado;
    }
}