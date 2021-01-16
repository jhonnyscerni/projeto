package br.com.siberius.projeto.api.model.input;

import br.com.siberius.projeto.domain.model.Clinica;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClinicaInputComSenhaModel extends ClinicaInputModel {

    @ApiModelProperty(example = "123", required = true)
    private String senha;
}
