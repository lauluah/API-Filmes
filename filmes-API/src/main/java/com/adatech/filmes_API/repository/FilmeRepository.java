package com.adatech.filmes_API.repository;

import com.adatech.filmes_API.model.Filme;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FilmeRepository extends JpaRepository<Filme, Long> {

  //  List<Filme> findByNomeContaining(String nome);

    Optional<Filme> findById(Long id);

    //List<Filme> findByGeneroContaining(String genero);

 //   List<Filme> findByNota(double nota);

   // List<Filme> findByCorAvaliacao(String corAvaliacao);
}
