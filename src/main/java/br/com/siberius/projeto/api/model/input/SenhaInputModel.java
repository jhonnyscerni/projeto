package br.com.siberius.projeto.api.model.input;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SenhaInputModel {

    @ApiModelProperty(example = "123", required = true)
    private String senhaAtual;

    @ApiModelProperty(example = "123", required = true)
    private String novaSenha;
}
