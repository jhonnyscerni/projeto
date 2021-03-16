package br.com.siberius.projeto.api.model.input;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class FormaPagamentoInputModel {

    @ApiModelProperty(example = "Cartão de crédito", required = true)
    @NotNull
    private String descricao;
}