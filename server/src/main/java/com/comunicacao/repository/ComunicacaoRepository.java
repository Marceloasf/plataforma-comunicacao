package com.comunicacao.repository;

import com.comunicacao.domain.Comunicacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComunicacaoRepository extends JpaRepository<Comunicacao, Long> {
}
