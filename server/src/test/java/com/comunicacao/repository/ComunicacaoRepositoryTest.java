package com.comunicacao.repository;

import com.comunicacao.domain.Comunicacao;
import com.comunicacao.enums.StatusComunicacaoEnum;
import com.comunicacao.enums.TipoComunicacaoEnum;
import com.comunicacao.fixtures.ComunicacaoFixtures;
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
@Sql(statements = "insert into comunicacao(id, dataenvio, destinatario, mensagem, tipo, status)" +
        "          values (1, '2021-01-01 10:00:00', 'teste@gmail.com', 'Mensagem teste', 'SMS', 'AGENDADA')")
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

    @Test
    public void findById() {

        Comunicacao result = this.repository.findById(1L).get();

        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getDataEnvio()).isEqualTo(LocalDateTime.of(2021, 1, 1, 10, 0, 0));
        assertThat(result.getDestinatario()).isEqualTo("teste@gmail.com");
        assertThat(result.getMensagem()).isEqualTo("Mensagem teste");
        assertThat(result.getTipo()).isEqualTo(TipoComunicacaoEnum.SMS);
        assertThat(result.getStatus()).isEqualTo(StatusComunicacaoEnum.AGENDADA);
    }

    @Test
    public void save() {

        Comunicacao comunicacao = ComunicacaoFixtures.criarNovaComunicacao();

        Comunicacao result = this.repository.save(comunicacao);

        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getDataEnvio()).isEqualTo(LocalDateTime.of(2021, 1, 1, 10, 30));
        assertThat(result.getDestinatario()).isEqualTo("fulano@gmail.com");
        assertThat(result.getMensagem()).isEqualTo("Mensagem teste");
        assertThat(result.getTipo()).isEqualTo(TipoComunicacaoEnum.EMAIL);
    }

    @Test
    public void deleteById() {

        Long id = 1L;

        assertThat(this.repository.findById(id)).isPresent();

        this.repository.deleteById(id);

        assertThat(this.repository.findById(id)).isNotPresent();
    }
}