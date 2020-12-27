package br.com.siberius.projeto.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Cidade {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COD_CIDADE")
    private Long id;

    @NotBlank
    @Column(name = "NM_CIDADE",nullable = false)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "COD_ESTADO",nullable = false)
    private Estado estado;

}