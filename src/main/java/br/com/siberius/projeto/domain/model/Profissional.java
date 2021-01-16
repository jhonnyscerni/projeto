package br.com.siberius.projeto.domain.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue("User")
@Data
public class Profissional extends Usuario {


    @Column(name = "FORMACAO_ACADEMICA")
    private String formacaoAcademica;

    @Column(name = "CONSELHO")
    private String conselho;

    @Column(name = "REGISTRO_CONSELHO")
    private String registroConselho;

    @Column(name = "CLINICA_ID")
    private Long clinicaId;

}
