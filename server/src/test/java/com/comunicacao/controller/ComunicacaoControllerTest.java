package com.comunicacao.controller;

import com.comunicacao.domain.Comunicacao;
import com.comunicacao.enums.TipoComunicacaoEnum;
import com.comunicacao.fixtures.ComunicacaoFixtures;
import com.comunicacao.service.ComunicacaoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@AutoConfigureMockMvc
@EnableSpringDataWebSupport
@RunWith(SpringRunner.class)
public class ComunicacaoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ComunicacaoService service;

    @Test
    public void findAll() throws Exception {

        List<Comunicacao> list = new ArrayList<>();
        list.add(new ComunicacaoFixtures().criarComunicacao());

        when(this.service.findAll()).thenReturn(list);

        this.mockMvc.perform(get("/api/comunicacoes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[*].id", hasItems(1)))
                .andExpect(jsonPath("$.[*].dataEnvio", hasItems("2021-01-01T10:30:00")))
                .andExpect(jsonPath("$.[*].destinatario", hasItems("fulano@gmail.com")))
                .andExpect(jsonPath("$.[*].mensagem", hasItems("Mensagem teste")))
                .andExpect(jsonPath("$.[*].tipo", hasItems(TipoComunicacaoEnum.EMAIL.toString())));

        verify(this.service).findAll();
        verifyNoMoreInteractions(this.service);
    }
}