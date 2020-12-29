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


    @Column(name = "CPF")
    private String cpf;

    @Column(name = "SEXO")
    private String sexo;

    @Column(name = "TELEFONE")
    private String telefone;

    @Column(name = "CELULAR")
    private String celular;

    @Column(name = "DT_NASCIMENTO")
    private Date dtNascimento;

    @Embedded
    private Endereco endereco;

    @Column(name = "NOME_MAE")
    private String nomeMae;

    @Column(name = "NOME_PAI")
    private String nomePai;

    @Column(name = "CPF_RESPONSAVEL")
    private String cpfResponsavel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COD_PROFISSIONAL",nullable = false)
    private Profissional profissional;
}
