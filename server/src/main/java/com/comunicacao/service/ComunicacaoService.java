package com.comunicacao.service;

import com.comunicacao.domain.Comunicacao;
import com.comunicacao.repository.ComunicacaoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ComunicacaoService {

    private final ComunicacaoRepository repository;

    public ComunicacaoService(ComunicacaoRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<Comunicacao> findAll() {

        return this.repository.findAll();
    }
}
