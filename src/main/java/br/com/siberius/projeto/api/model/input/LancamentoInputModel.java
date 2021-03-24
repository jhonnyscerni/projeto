package br.com.siberius.projeto.api.model.input;

import br.com.siberius.projeto.api.model.input.id.CategoriaLancamentoInputIdModel;
import br.com.siberius.projeto.api.model.input.id.FormaPagamentoInputIdModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import javax.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LancamentoInputModel {

    @ApiModelProperty(example = "1")
    private Long id;

    @ApiModelProperty(example = "1.00")
    private BigDecimal valorTotal;

    @ApiModelProperty(example = "pagamento da consulta")
    private String descricao;

    private FormaPagamentoInputIdModel formaPagamento;

    private CategoriaLancamentoInputIdModel categoria;

    @ApiModelProperty(example = "2019-12-01T18:09:02.70844Z", position = 5)
    private OffsetDateTime dtLancamento;

    private ConsultaInputModel consulta;

    @ApiModelProperty(example = "1")
    private Long profissionalId;

    @ApiModelProperty(example = "1")
    private Long clinicaId;
}
