package com.comunicacao.domain;

import com.comunicacao.enums.StatusComunicacaoEnum;
import com.comunicacao.enums.TipoComunicacaoEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "comunicacao")
@EqualsAndHashCode(of = "id")
public class Comunicacao {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comunicacao_sequence")
    @SequenceGenerator(name = "comunicacao_sequence", sequenceName = "comunicacao_sequence", allocationSize = 1)
    private Long id;

    @NotNull
    @Column(name = "dataenvio")
    private LocalDateTime dataEnvio;

    @NotBlank
    @Length(max = 200)
    private String destinatario;

    @NotBlank
    @Length(max = 2000)
    private String mensagem;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TipoComunicacaoEnum tipo;

    @NotNull
    @Enumerated(EnumType.STRING)
    private StatusComunicacaoEnum status = StatusComunicacaoEnum.AGENDADA;
}
