package com.comunicacao.fixtures;

import com.comunicacao.domain.Comunicacao;
import com.comunicacao.enums.TipoComunicacaoEnum;

import java.time.LocalDateTime;

public class ComunicacaoFixtures {

    public Comunicacao criarComunicacao() {

        Comunicacao comunicacao = new Comunicacao();
        comunicacao.setId(1L);
        comunicacao.setDataEnvio(LocalDateTime.of(2021, 1, 1, 10, 30));
        comunicacao.setDestinatario("fulano@gmail.com");
        comunicacao.setMensagem("Mensagem teste");
        comunicacao.setTipo(TipoComunicacaoEnum.EMAIL);

        return comunicacao;
    }
}
