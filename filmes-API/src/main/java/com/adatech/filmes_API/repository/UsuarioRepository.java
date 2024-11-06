package com.adatech.filmes_API.repository;

import com.adatech.filmes_API.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    List<Usuario> findByNomeContaining(String nome);

 //   List<Usuario> findByGeneroContaining(String genero);

    Optional<Usuario> findByCpf(String cpf);

    Optional<Usuario> findByEmail(String email);

    Page<Usuario> findAll(Pageable pageable);
}
