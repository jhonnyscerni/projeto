package br.com.siberius.projeto.api.model;

import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GrupoModel {

    @ApiModelProperty(example = "1")
    private Long id;

    @ApiModelProperty(example = "Administrador")
    private String nome;

    @ApiModelProperty(example = "CONSULTA_USUARIOS")
    private List<PermissaoModel> permissoes = new ArrayList<>();
}
