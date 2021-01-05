package br.com.siberius.projeto.domain.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue("Patient")
@Data
public class Paciente extends Usuario {


    @Column(name = "NOME_MAE")
    private String nomeMae;

    @Column(name = "NOME_PAI")
    private String nomePai;

    @Column(name = "CPF_RESPONSAVEL")
    private String cpfResponsavel;

    @Column(name = "PROFISSIONAL_ID")
    private Long profissionalId;
}
