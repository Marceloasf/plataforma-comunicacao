package com.comunicacao.service;

import com.comunicacao.domain.Comunicacao;
import com.comunicacao.enums.StatusComunicacaoEnum;
import com.comunicacao.exception.RegistroNaoEncontradoException;
import com.comunicacao.repository.ComunicacaoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ComunicacaoService {

    private final ComunicacaoRepository repository;

    public ComunicacaoService(ComunicacaoRepository repository) {
        this.repository = repository;
    }

    public void saveComunicacao(Comunicacao novaComunicacao) {

        this.repository.save(novaComunicacao);
    }

    @Transactional(readOnly = true)
    public StatusComunicacaoEnum findStatusByComunicacaoId(Long id) {

        Comunicacao comunicacao = this.repository.findById(id).orElseThrow(RegistroNaoEncontradoException::new);

        return comunicacao.getStatus();
    }

    public void deleteById(Long id) {

        this.repository.deleteById(id);
    }
}
