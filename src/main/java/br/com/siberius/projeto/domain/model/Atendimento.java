package br.com.siberius.projeto.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "ATENDIMENTO")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Atendimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "COD_ATENDIMENTO")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COD_CONSULTA",nullable = false)
    private Consulta consulta;

    @Column(name = "OBJETIVO_SESSAO")
    private String objetivoSessao;

    @Column(name = "DESCREVER_SESSAO")
    private String descreveSessao;
}
