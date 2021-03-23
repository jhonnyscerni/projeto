package br.com.siberius.projeto.api.model.input.id;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoriaLancamentoInputIdModel {

    @ApiModelProperty(example = "1")
    private Long id;

}
