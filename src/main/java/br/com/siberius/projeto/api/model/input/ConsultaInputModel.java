package br.com.siberius.projeto.api.model.input;

import br.com.siberius.projeto.api.model.ClinicaModel;
import br.com.siberius.projeto.api.model.PacienteModel;
import br.com.siberius.projeto.api.model.ProfissionalModel;
import br.com.siberius.projeto.domain.model.enums.ConvenioEnum;
import br.com.siberius.projeto.domain.model.enums.ProcedimentoEnum;
import br.com.siberius.projeto.domain.model.enums.StatusConsultaEnum;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConsultaInputModel {

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

    @ApiModelProperty(example = "Titulo da Consulta")
    private String title;

    @ApiModelProperty(example = "Cor do status da consulta")
    private String className;

    private ClinicaInputModel clinica;

}
