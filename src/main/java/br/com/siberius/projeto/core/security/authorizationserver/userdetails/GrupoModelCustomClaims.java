package br.com.siberius.projeto.core.security.authorizationserver.userdetails;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GrupoModelCustomClaims {

    @ApiModelProperty(example = "1")
    private Long id;

    @ApiModelProperty(example = "Administrador")
    private String nome;
}
