package com.comunicacao.service;

import com.comunicacao.domain.Comunicacao;
import com.comunicacao.enums.StatusComunicacaoEnum;
import com.comunicacao.exception.RegistroNaoEncontradoException;
import com.comunicacao.fixtures.ComunicacaoFixtures;
import com.comunicacao.repository.ComunicacaoRepository;
import com.comunicacao.validator.ComunicacaoValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ComunicacaoServiceTest {

    @Mock
    private ComunicacaoRepository repository;

    @Mock
    private ComunicacaoValidator validator;

    @InjectMocks
    private ComunicacaoService service;


    @Test
    public void findById() {

        var comunicacao = ComunicacaoFixtures.criarComunicacao();

        when(this.repository.findById(comunicacao.getId())).thenReturn(Optional.of(comunicacao));

        this.service.findById(comunicacao.getId());

        verify(this.repository).findById(comunicacao.getId());
        verifyNoMoreInteractions(this.repository);
    }

    @Test
    public void findByIdRegistroNaoEncontradoException() {

        Long id = 1L;

        when(this.repository.findById(id)).thenReturn(Optional.empty());

        var exception = assertThrows(RegistroNaoEncontradoException.class, () ->
                this.service.findById(id)
        );

        assertThat(exception.getMessage()).isEqualTo("O registro não foi encontrado.");

        verify(this.repository).findById(id);
        verifyNoMoreInteractions(this.repository);
    }

    @Test
    public void saveComunicacao() {

        var novaComunicacao = ComunicacaoFixtures.criarNovaComunicacao();

        when(this.repository.save(any(Comunicacao.class))).thenReturn(novaComunicacao);

        this.service.saveComunicacao(novaComunicacao);

        verify(this.repository).save(any(Comunicacao.class));
        verifyNoMoreInteractions(this.repository);
    }

    @Test
    public void findStatusByComunicacaoId() {

        var comunicacao = ComunicacaoFixtures.criarComunicacao();

        when(this.repository.findById(comunicacao.getId())).thenReturn(Optional.of(comunicacao));

        var status = this.service.findStatusByComunicacaoId(comunicacao.getId());

        assertThat(status).isEqualTo(StatusComunicacaoEnum.AGENDADA);

        verify(this.repository).findById(comunicacao.getId());
        verifyNoMoreInteractions(this.repository);
    }

    @Test
    public void findStatusByComunicacaoIdException() {

        Long id = 1L;

        when(this.repository.findById(id)).thenReturn(Optional.empty());

        var exception = assertThrows(RegistroNaoEncontradoException.class, () ->
                this.service.findStatusByComunicacaoId(id)
        );

        assertThat(exception.getMessage()).isEqualTo("O registro não foi encontrado.");

        verify(this.repository).findById(id);
        verifyNoMoreInteractions(this.repository);
    }

    @Test
    public void deleteById() {

        var comunicacao = ComunicacaoFixtures.criarComunicacao();

        when(this.repository.findById(comunicacao.getId())).thenReturn(Optional.of(comunicacao));
        doNothing().when(this.repository).delete(comunicacao);

        this.service.deleteById(comunicacao.getId());

        verify(this.repository).findById(comunicacao.getId());
        verify(this.repository).delete(comunicacao);
        verifyNoMoreInteractions(this.repository);
    }
}