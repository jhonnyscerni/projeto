package br.com.siberius.projeto.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "CATEGORIA_LANCAMENTO")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CategoriaLancamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "COD_LANCAMENTO")
    private Long id;

    @Column(name = "NM_CATEGORIA_LANC")
    private String nomeCategoriaLanc;
}
