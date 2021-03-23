package br.com.siberius.projeto.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoriaLancamentoModel {

    @ApiModelProperty(example = "1")
    private Long id;

    @ApiModelProperty(example = "DESPESA")
    private String nomeCategoriaLanc;
}
