package br.com.siberius.projeto.api.model.input;

import io.swagger.annotations.ApiModelProperty;
import javax.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PermissaoInputModel {

    @ApiModelProperty(example = "1")
    private Long Id;

    @ApiModelProperty(example = "SEG_CONSULTAR_USUARIOS")
    @Column(nullable = false)
    private String nome;

    @ApiModelProperty(example = "Permite Consultar os Usuarios Cadastrados")
    @Column(nullable = false)
    private String descricao;
}
