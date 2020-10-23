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
@Table(name = "GRUPO")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Grupo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "COD_GRUPO")
    private Long id;

    @Column(name = "NM_GRUPO")
    private String nome;
}
