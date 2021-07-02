package com.comunicacao.exception;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;

public class RegistroNaoEncontradoExceptionTest {

    @Test
    public void deveLancarRegistroNaoEncontradoException() {

        Exception exception = assertThrows(RegistroNaoEncontradoException.class, () -> {
            throw new RegistroNaoEncontradoException();
        });

        assertThat(exception.getMessage()).isEqualTo("O registro não foi encontrado.");
    }
}