package br.com.siberius.projeto.api.model;

import io.swagger.annotations.ApiModelProperty;
import java.time.OffsetDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GrupoModel {

    @ApiModelProperty(example = "1")
    private Long id;

    @ApiModelProperty(example = "Administrador")
    private String nome;
}
