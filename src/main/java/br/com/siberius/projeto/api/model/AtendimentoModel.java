package br.com.siberius.projeto.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AtendimentoModel {

    @ApiModelProperty(example = "1")
    private Long id;

    private ConsultaModel consulta;

    @ApiModelProperty(example = "objetivo da sessão")
    private String objetivoSessao;

    @ApiModelProperty(example = "descritivo da sessão")
    private String descreveSessao;
}
