package br.com.siberius.projeto.api.openapi.controller;


import br.com.siberius.projeto.domain.model.dto.EstatisticaSexo;
import br.com.siberius.projeto.domain.model.dto.EstatisticaStatus;
import br.com.siberius.projeto.domain.repository.StatusConsultaQueryService;
import br.com.siberius.projeto.infrastructure.service.filter.EstatisticaSexoFilter;
import br.com.siberius.projeto.infrastructure.service.filter.EstatisticaStatusFilter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Api(tags = "Dashboard do Profissional")
public interface DashboardProfissionalControllerOpenApi {


    @ApiOperation("Busca a quantidade de consultas finalizadas do Profissional")
    Long countConsultaByStatusConsultaEnumFinalizado(Long profissionalId);

    @ApiOperation("Busca a quantidade de consultas Confirmadas do Profissional")
    Long countConsultaByStatusConsultaEnumConfirmada(Long profissionalId);

    @ApiOperation("Busca a quantidade de consultas Agendadas do Profissional")
    Long countConsultaByStatusConsultaEnumAgendada(Long profissionalId);

    @ApiOperation("Busca a quantidade de consultas Canceladas do Profissional")
    Long countConsultaByStatusConsultaEnumCanceladas(Long profissionalId);

    @ApiOperation("Busca a quantidade de pacientes ativados do Profissional")
    Long countPacienteCadastradoAtivado(Long profissionalId);

    @ApiOperation("Busca a estatistica dos status do paciente do Profissional")
    List<EstatisticaStatus> estatisticaStatus(EstatisticaStatusFilter filter, String timeOffset);

    @ApiOperation("Busca a estatistica dos sexos do paciente do Profissional")
    List<EstatisticaSexo> estatisticaSexo(EstatisticaSexoFilter filter, String timeOffset);


}