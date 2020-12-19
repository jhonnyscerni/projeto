package br.com.siberius.projeto.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PermissaoModel {

    @ApiModelProperty(example = "1")
    private Long Id;

    @ApiModelProperty(example = "SEG_CONSULTAR_USUARIOS")
    private String nome;

    @ApiModelProperty(example = "Permite Consultar os Usuarios Cadastrados")
    private String descricao;
}
