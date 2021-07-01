package com.comunicacao.service;

import com.comunicacao.domain.Comunicacao;
import com.comunicacao.fixtures.ComunicacaoFixtures;
import com.comunicacao.repository.ComunicacaoRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ComunicacaoServiceTest {

    @Mock
    private ComunicacaoRepository repository;

    @InjectMocks
    private ComunicacaoService service;

    @Test
    public void findAll() {

        List<Comunicacao> list = new ArrayList<>();
        list.add(new ComunicacaoFixtures().criarComunicacao());

        when(this.repository.findAll()).thenReturn(list);

        this.service.findAll();

        verify(this.repository).findAll();
        verifyNoMoreInteractions(this.repository);
    }
}