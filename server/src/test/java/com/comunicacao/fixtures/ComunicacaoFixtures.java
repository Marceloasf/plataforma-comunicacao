package com.comunicacao.fixtures;

import com.comunicacao.domain.Comunicacao;
import com.comunicacao.enums.StatusComunicacaoEnum;
import com.comunicacao.enums.TipoComunicacaoEnum;

import java.time.LocalDateTime;

public final class ComunicacaoFixtures {

    private ComunicacaoFixtures() {
    }

    public static Comunicacao criarComunicacao() {

        Comunicacao comunicacao = new Comunicacao();
        comunicacao.setId(1L);
        setDadosComunicacao(comunicacao);

        return comunicacao;
    }

    public static Comunicacao criarNovaComunicacao() {

        Comunicacao comunicacao = new Comunicacao();
        setDadosComunicacao(comunicacao);

        return comunicacao;
    }

    private static void setDadosComunicacao(Comunicacao comunicacao) {

        comunicacao.setDataEnvio(LocalDateTime.of(2021, 1, 1, 10, 30));
        comunicacao.setDestinatario("fulano@gmail.com");
        comunicacao.setMensagem("Mensagem teste");
        comunicacao.setTipo(TipoComunicacaoEnum.EMAIL);
        comunicacao.setStatus(StatusComunicacaoEnum.AGENDADA);
    }
}
