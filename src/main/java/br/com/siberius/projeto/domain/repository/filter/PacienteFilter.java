package br.com.siberius.projeto.domain.repository.filter;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import java.time.OffsetDateTime;

@Setter
@Getter
public class PacienteFilter {

    private String nome;

    private String email;

    @DateTimeFormat(iso = ISO.DATE_TIME)
    private OffsetDateTime dataCriacaoInicio;

    @DateTimeFormat(iso = ISO.DATE_TIME)
    private OffsetDateTime dataCriacaoFim;

    @ApiModelProperty(example = "1", value = "ID do profissional para filtro da pesquisa")
    private Long profissionalId;

    @ApiModelProperty(example = "1", value = "ID da clinica para filtro da pesquisa")
    private Long clinicaId;

}
