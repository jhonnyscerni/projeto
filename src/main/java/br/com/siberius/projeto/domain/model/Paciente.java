package br.com.siberius.projeto.domain.model;

import javax.persistence.*;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@Entity
@DiscriminatorValue("Patient")
@Data
public class Paciente extends Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "COD_PACIENTE")
    private Long id;

    @Column(name = "NOME_MAE")
    private String nomeMae;

    @Column(name = "NOME_PAI")
    private String nomePai;

    @Column(name = "CPF_RESPONSAVEL")
    private String cpfResponsavel;

    @Column(name = "PROFISSIONAL_ID")
    private Long profissionalId;

    @Column(name = "CLINICA_ID")
    private Long clinicaId;
}
