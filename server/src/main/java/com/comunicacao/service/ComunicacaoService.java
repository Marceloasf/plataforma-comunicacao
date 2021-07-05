package com.comunicacao.service;

import com.comunicacao.domain.Comunicacao;
import com.comunicacao.enums.StatusComunicacaoEnum;
import com.comunicacao.exception.RegistroNaoEncontradoException;
import com.comunicacao.repository.ComunicacaoRepository;
import com.comunicacao.validator.ComunicacaoValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ComunicacaoService {

    private final ComunicacaoRepository repository;
    private final ComunicacaoValidator validator;

    public ComunicacaoService(ComunicacaoRepository repository, ComunicacaoValidator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    public Comunicacao findById(Long id) {

        return this.repository.findById(id).orElseThrow(RegistroNaoEncontradoException::new);
    }

    public Long saveComunicacao(Comunicacao novaComunicacao) {

        validator.validarNovoRegistro(novaComunicacao);

        return this.repository.save(novaComunicacao).getId();
    }

    @Transactional(readOnly = true)
    public StatusComunicacaoEnum findStatusByComunicacaoId(Long id) {

        return this.findById(id).getStatus();
    }

    public void deleteById(Long id) {

        this.repository.delete(this.findById(id));
    }
}
