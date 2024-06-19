package com.example.API.Repository;

import com.example.API.Domain.Avaliacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {
}

