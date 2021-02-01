package br.com.siberius.projeto.api.openapi.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Dashboard do Profissional")
public interface DashboardProfissionalControllerOpenApi {


    @ApiOperation("Busca a quantidade de consultas finalizadas do Profissional")
    Long countConsultaByStatusConsultaEnumFinalizado(Long profissionalId);

    @ApiOperation("Busca a quantidade de consultas Confirmadas do Profissional")
    Long countConsultaByStatusConsultaEnumConfirmada(Long profissionalId);

    @ApiOperation("Busca a quantidade de consultas Agendadas do Profissional")
    Long countConsultaByStatusConsultaEnumAgendada(Long profissionalId);

    @ApiOperation("Busca a quantidade de pacientes ativados do Profissional")
    Long countPacienteCadastradoAtivado(Long profissionalId);


}