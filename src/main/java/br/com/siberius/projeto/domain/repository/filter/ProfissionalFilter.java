package br.com.siberius.projeto.domain.repository.filter;

import io.swagger.annotations.ApiModelProperty;
import java.time.OffsetDateTime;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Setter
@Getter
public class ProfissionalFilter {

    private String nome;

    private String email;

    @DateTimeFormat(iso = ISO.DATE_TIME)
    private OffsetDateTime dataCriacaoInicio;

    @DateTimeFormat(iso = ISO.DATE_TIME)
    private OffsetDateTime dataCriacaoFim;

    @ApiModelProperty(example = "1", value = "ID da clinica para filtro da pesquisa")
    private Long clinicaId;

}
