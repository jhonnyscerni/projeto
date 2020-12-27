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
@DiscriminatorValue("Patient")
@Data
public class Paciente extends Usuario {

    @Column(name = "TELEFONE")
    private String telefone;

    @Column(name = "CELULAR")
    private String celular;

    @Column(name = "DT_NASCIMENTO")
    private Date dtNascimento;

    @Embedded
    private Endereco endereco;
}
