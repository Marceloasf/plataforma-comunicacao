package com.comunicacao.domain;

import com.comunicacao.enums.TipoComunicacaoEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
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
    private Long id;

    @NotNull
    @Column(name = "dataenvio")
    private LocalDateTime dataEnvio;

    @NotBlank
    @Length(max = 200)
    private String destinatario;

    @Length(max = 2000)
    private String mensagem = StringUtils.EMPTY;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TipoComunicacaoEnum tipo;
}
