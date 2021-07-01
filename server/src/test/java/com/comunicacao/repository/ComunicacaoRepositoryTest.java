package com.comunicacao.repository;

import com.comunicacao.domain.Comunicacao;
import com.comunicacao.enums.TipoComunicacaoEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Rollback
@DataJpaTest
@RunWith(SpringRunner.class)
@Sql(statements = "insert into comunicacao(id, dataenvio, destinatario, mensagem, tipo)" +
        "          values (1, '2021-01-01 10:00:00', 'teste@gmail.com', 'Mensagem teste', 'SMS')")
public class ComunicacaoRepositoryTest {

    @Autowired
    private ComunicacaoRepository repository;

    @Test
    public void findAll() {

        List<Comunicacao> result = this.repository.findAll();

        assertThat(result).extracting(Comunicacao::getId).containsExactly(1L);
        assertThat(result).extracting(Comunicacao::getDataEnvio).containsExactly(LocalDateTime.of(2021, 1, 1, 10, 0, 0));
        assertThat(result).extracting(Comunicacao::getDestinatario).containsExactly("teste@gmail.com");
        assertThat(result).extracting(Comunicacao::getMensagem).containsExactly("Mensagem teste");
        assertThat(result).extracting(Comunicacao::getTipo).containsExactly(TipoComunicacaoEnum.SMS);
    }
}