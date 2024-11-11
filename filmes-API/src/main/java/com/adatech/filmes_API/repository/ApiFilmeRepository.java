package com.adatech.filmes_API.repository;

import com.adatech.filmes_API.model.ApiFilme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApiFilmeRepository extends JpaRepository<ApiFilme, Long> {
}