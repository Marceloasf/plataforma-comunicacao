package com.comunicacao.validator;

import com.comunicacao.domain.Comunicacao;
import com.comunicacao.enums.StatusComunicacaoEnum;
import com.comunicacao.exception.ComunicacaoException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ComunicacaoValidator {

    public void validarNovoRegistro(Comunicacao comunicacao) {

        if (Optional.ofNullable(comunicacao.getId()).isPresent()) {
            throw new ComunicacaoException("A comunicação não pode ser agendada com um identificador inserido.");
        }

        if (!StatusComunicacaoEnum.AGENDADA.equals(comunicacao.getStatus())) {
            throw new ComunicacaoException("A comunicação não pode ser agendada com o tipo diferente de 'AGENDADA'.");
        }
    }
}
