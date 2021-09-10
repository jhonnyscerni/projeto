package br.com.siberius.projeto.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "LANCAMENTO")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Lancamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "COD_LANCAMENTO")
    private Long id;

    @Column(name = "VALOR_TOTAL")
    private BigDecimal valorTotal;

    @Column(name = "DESCRICAO")
    private String descricao;

    @ManyToOne(fetch = FetchType.LAZY)
    private FormaPagamento formaPagamento;

    @ManyToOne(fetch = FetchType.LAZY)
    private CategoriaLancamento categoria;

    @Column(name = "DT_LANCAMENTO")
    private OffsetDateTime dtLancamento;

    @OneToOne
    private Consulta consulta;

    @Column(name = "PROFISSIONAL_ID")
    private Long profissionalId;

    @Column(name = "CLINICA_ID")
    private Long clinicaId;

}
