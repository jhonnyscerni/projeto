package br.com.siberius.projeto.api.model.input;

import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GrupoInputModel {

    @ApiModelProperty(example = "3")
    private Long id;

    @ApiModelProperty(example = "Profissional", required = true)
    @NotBlank
    private String nome;

}
