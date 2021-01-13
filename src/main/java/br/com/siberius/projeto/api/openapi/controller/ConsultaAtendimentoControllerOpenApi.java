package br.com.siberius.projeto.api.openapi.controller;

import br.com.siberius.projeto.api.exceptionhandler.Problem;
import br.com.siberius.projeto.api.model.AtendimentoModel;
import br.com.siberius.projeto.api.model.input.AtendimentoInputModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Atender uma Consulta")
public interface ConsultaAtendimentoControllerOpenApi {



    @ApiOperation("Cadastra um produto de um restaurante")
    @ApiResponses({
        @ApiResponse(code = 201, message = "Atendimento cadastrado"),
        @ApiResponse(code = 404, message = "Consulta não encontrado", response = Problem.class)
    })
    AtendimentoModel adicionar(
        @ApiParam(value = "ID da Consulta", example = "1", required = true)
            Long consultaId,

        @ApiParam(name = "corpo", value = "Representação de um novo atendimento", required = true)
            AtendimentoInputModel atendimentoInputModel);
}