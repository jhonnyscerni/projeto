package br.com.siberius.projeto.api.model;

import br.com.siberius.projeto.domain.model.enums.ConvenioEnum;
import br.com.siberius.projeto.domain.model.enums.ProcedimentoEnum;
import br.com.siberius.projeto.domain.model.enums.StatusConsultaEnum;
import io.swagger.annotations.ApiModelProperty;
import java.time.OffsetDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConsultaModel {

    @ApiModelProperty(example = "1")
    private Long id;

    private PacienteModel paciente;

    private ProfissionalModel profissional;

    @ApiModelProperty(example = "2020-10-23T18:16:59.517Z")
    private OffsetDateTime dataHora;

    @ApiModelProperty(example = "Clinica")
    private String localDeAtendimento;

    private ProcedimentoEnum procedimentoEnum;

    private StatusConsultaEnum statusConsultaEnum;

    private ConvenioEnum convenioEnum;

    @ApiModelProperty(example = "Observacoes")
    private String observacoes;
}
