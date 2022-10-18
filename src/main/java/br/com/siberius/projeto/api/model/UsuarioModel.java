package br.com.siberius.projeto.api.model;

import io.swagger.annotations.ApiModelProperty;
import java.time.OffsetDateTime;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioModel {

    @ApiModelProperty(example = "1")
    private Long id;

    @ApiModelProperty(example = "Jhonny Scerni")
    private String nome;

    @ApiModelProperty(example = "jhonnyscerni@gmail.com")
    private String email;

    @ApiModelProperty(example = "jhonnyscerni@gmail.com")
    private String senha;

    @ApiModelProperty(example = "2020-10-23T18:16:59.517Z")
    private OffsetDateTime dataCadastro;

    @ApiModelProperty(example = "Administrador", required = true)
    private List<GrupoModel> grupos;

    @ApiModelProperty(example = "false")
    private String ativado;
}
