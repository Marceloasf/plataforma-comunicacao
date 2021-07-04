package com.comunicacao.validator;

import com.comunicacao.domain.Comunicacao;
import com.comunicacao.enums.StatusComunicacaoEnum;
import com.comunicacao.exception.ComunicacaoException;
import com.comunicacao.fixtures.ComunicacaoFixtures;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;

public class ComunicacaoValidatorTest {

    private final ComunicacaoValidator validator = new ComunicacaoValidator();

    @Test
    public void validarNovoRegistroComIdInserido() {

        Exception exception = assertThrows(ComunicacaoException.class, () ->
                this.validator.validarNovoRegistro(ComunicacaoFixtures.criarComunicacao())
        );

        assertThat(exception.getMessage()).isEqualTo("A comunicação não pode ser agendada com um identificador inserido.");
    }

    @Test
    public void validarNovoRegistroComStatusDiferenteDeAgendada() {

        Comunicacao comunicacao = ComunicacaoFixtures.criarNovaComunicacao();
        comunicacao.setStatus(StatusComunicacaoEnum.ENVIADA);

        Exception exception = assertThrows(ComunicacaoException.class, () ->
                this.validator.validarNovoRegistro(comunicacao)
        );

        assertThat(exception.getMessage()).isEqualTo("A comunicação não pode ser agendada com o tipo diferente de 'AGENDADA'.");
    }
}
