package br.com.siberius.projeto.api.openapi.controller;


import br.com.siberius.projeto.api.exceptionhandler.Problem;
import br.com.siberius.projeto.api.model.LancamentoModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Lançamentos Financeiros por ID Consulta")
public interface LancamentoConsultaControllerOpenApi {


    @ApiOperation("Busca um lancamento por ID da Consulta")
    @ApiResponses({
        @ApiResponse(code = 400, message = "ID da consulta inválido", response = Problem.class),
        @ApiResponse(code = 404, message = "Lançamento não encontrado", response = Problem.class)
    })
    LancamentoModel buscarPorConsulta(
        @ApiParam(value = "ID da consulta", example = "1", required = true)
            Long consultaId);

}