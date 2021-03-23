package br.com.siberius.projeto.api.model;

import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LancamentoModel {

    private Long id;

    @ApiModelProperty(example = "1.00")
    private BigDecimal valorTotal;

    @ApiModelProperty(example = "pagamento da consulta")
    private String descricao;

    private FormaPagamentoModel formaPagamento;

    private CategoriaLancamentoModel categoria;

    @ApiModelProperty(example = "2019-12-01T18:09:02.70844Z", position = 5)
    private OffsetDateTime dtLancamento;

    private ConsultaModel consulta;
}
