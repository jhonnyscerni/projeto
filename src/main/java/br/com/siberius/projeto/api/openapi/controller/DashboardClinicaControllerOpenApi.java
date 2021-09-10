package br.com.siberius.projeto.api.openapi.controller;


import br.com.siberius.projeto.domain.model.dto.EstatisticaSexo;
import br.com.siberius.projeto.domain.model.dto.EstatisticaStatus;
import br.com.siberius.projeto.infrastructure.service.filter.EstatisticaSexoFilter;
import br.com.siberius.projeto.infrastructure.service.filter.EstatisticaStatusFilter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

@Api(tags = "Dashboard da Clinica")
public interface DashboardClinicaControllerOpenApi {

    @ApiOperation("Busca a Quantidade Total de Paciente que a Clinica Possui")
    Long countPacientesAtivo(Long clinicaId);

    @ApiOperation("Busca a Quantidade Total de Profissionais que a Clinica Possui")
    Long countProfissionaisAtivo(Long clinicaId);

    @ApiOperation("Busca a Quantidade Total de Consultas que a Clinica Possui")
    Long countConsultasTotais(Long clinicaId);

    @ApiOperation("Busca a Quantidade Total de atendimento que a Clinica Possui")
    Long countConsultaByStatusConsultaEnumFinalizado(Long clinicaId);

    @ApiOperation("Busca a estatistica dos status do paciente da Clinica")
    List<EstatisticaStatus> estatisticaStatus(EstatisticaStatusFilter filter, String timeOffset);

    @ApiOperation("Busca a estatistica dos sexos do paciente da clinica")
    List<EstatisticaSexo> estatisticaSexo(EstatisticaSexoFilter filter, String timeOffset);


}