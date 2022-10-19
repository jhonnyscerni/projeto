package br.com.siberius.projeto.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@Table(name = "PERMISSAO")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Permissao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "COD_PERMISSAO")
    private Long Id;

    @Column(name = "NM_PERMISSAO", nullable = false)
    private String nome;

    @Column(name = "DESC_PERMISSAO", nullable = false)
    private String descricao;

}
