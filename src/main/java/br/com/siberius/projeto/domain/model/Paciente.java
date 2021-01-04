package br.com.siberius.projeto.domain.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COD_PROFISSIONAL")
    private Profissional profissional;
}
