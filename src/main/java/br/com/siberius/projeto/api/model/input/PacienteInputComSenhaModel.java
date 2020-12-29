package br.com.siberius.projeto.api.model.input;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PacienteInputComSenhaModel extends PacienteInputModel {

    @ApiModelProperty(example = "123", required = true)
    private String senha;
}
