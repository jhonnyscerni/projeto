package br.com.siberius.projeto.domain.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue("User")
@Data
public class Profissional extends Usuario {

    @Column(name = "CPF")
    private String cpf;

    @Column(name = "SEXO")
    private String sexo;

    @Column(name = "FORMACAO_ACADEMICA")
    private String formacaoAcademica;

    @Column(name = "CONSELHO")
    private String conselho;

    @Column(name = "REGISTRO_CONSELHO")
    private String registroConselho;

    @Column(name = "TELEFONE")
    private String telefone;

    @Column(name = "CELULAR")
    private String celular;

    @Column(name = "DT_NASCIMENTO")
    private Date dtNascimento;

    @Embedded
    private Endereco endereco;

    public Profissional() {
    }
}
