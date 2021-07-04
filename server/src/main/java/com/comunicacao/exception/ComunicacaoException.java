package com.comunicacao.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ComunicacaoException extends RuntimeException {

    public ComunicacaoException(String message) {
        super(message);
    }
}
