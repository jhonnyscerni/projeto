package br.com.siberius.projeto.api.model.input;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoriaLancamentoInputModel {

    @ApiModelProperty(example = "1")
    private Long id;

    @ApiModelProperty(example = "DESPESA")
    private String nomeCategoriaLanc;
}
