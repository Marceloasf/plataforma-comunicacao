package com.comunicacao.controller;

import com.comunicacao.domain.Comunicacao;
import com.comunicacao.enums.StatusComunicacaoEnum;
import com.comunicacao.fixtures.ComunicacaoFixtures;
import com.comunicacao.service.ComunicacaoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@AutoConfigureMockMvc
@EnableSpringDataWebSupport
@RunWith(SpringRunner.class)
public class ComunicacaoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ComunicacaoService service;

    @Test
    public void saveComunicacao() throws Exception {

        Comunicacao novaComunicacao = ComunicacaoFixtures.criarNovaComunicacao();

        doNothing().when(this.service).saveComunicacao(novaComunicacao);

        this.mockMvc.perform(post("/api/comunicacoes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(novaComunicacao)))
                 .andExpect(status().isCreated());

        verify(this.service).saveComunicacao(novaComunicacao);
        verifyNoMoreInteractions(this.service);
    }

    @Test
    public void findStatusByComunicacaoId() throws Exception {

        Long id = 1L;

        when(this.service.findStatusByComunicacaoId(id)).thenReturn(StatusComunicacaoEnum.AGENDADA);

        this.mockMvc.perform(get("/api/comunicacoes/{id}/status", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", equalTo(StatusComunicacaoEnum.AGENDADA.toString())));

        verify(this.service).findStatusByComunicacaoId(id);
        verifyNoMoreInteractions(this.service);
    }

    @Test
    public void deleteById() throws Exception {

        Long id = 1L;

        doNothing().when(this.service).deleteById(id);

        this.mockMvc.perform(delete("/api/comunicacoes/{id}", id))
                .andExpect(status().isNoContent());

        verify(this.service).deleteById(id);
        verifyNoMoreInteractions(this.service);
    }
}