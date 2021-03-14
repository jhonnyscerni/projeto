package br.com.siberius.projeto.domain.model;

import lombok.Data;

import javax.persistence.*;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@Entity
@DiscriminatorValue("User")
@Data
public class Profissional extends Usuario {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "COD_PROFISSIONAL")
    private Long id;

    @Column(name = "FORMACAO_ACADEMICA")
    private String formacaoAcademica;

    @Column(name = "CONSELHO")
    private String conselho;

    @Column(name = "REGISTRO_CONSELHO")
    private String registroConselho;

    @Column(name = "CLINICA_ID")
    private Long clinicaId;

    @OneToOne
    private FotoPerfil fotoPerfil;

}
