package br.com.siberius.projeto.api.openapi.controller;

import br.com.siberius.projeto.api.exceptionhandler.Problem;
import br.com.siberius.projeto.api.model.ConsultaModel;
import br.com.siberius.projeto.api.model.PacienteModel;
import br.com.siberius.projeto.api.model.input.ConsultaInputModel;
import br.com.siberius.projeto.domain.repository.filter.ConsultaFilter;
import br.com.siberius.projeto.domain.repository.filter.PacienteFilter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Api(tags = "Consultas")
public interface ConsultaControllerOpenApi {

    @ApiImplicitParams({
        @ApiImplicitParam(value = "Nomes das propriedades para filtrar na resposta, separados por vírgula",
            name = "campos", paramType = "query", type = "string")
    })
    @ApiOperation("Pesquisar os consultas")
    Page<ConsultaModel> pesquisar(ConsultaFilter filter, Pageable pageable);

    @ApiOperation("Pesquisar os pacientes")
    List<ConsultaModel> pesquisar(ConsultaFilter filter);

    @ApiOperation("Busca uma consulta por ID")
    @ApiResponses({
        @ApiResponse(code = 400, message = "ID da consulta inválida", response = Problem.class),
        @ApiResponse(code = 404, message = "Consulta não encontrado", response = Problem.class)
    })
    ConsultaModel buscar(
        @ApiParam(value = "ID de uma consulta", example = "1", required = true)
            Long consultaId);

    @ApiOperation("Cadastra uma consulta")
    @ApiResponses({
        @ApiResponse(code = 201, message = "Consulta cadastrada"),
    })
    ConsultaModel adicionar(
        @ApiParam(name = "corpo", value = "Representação de um nova consulta", required = true)
            ConsultaInputModel consultaInputModel);

    @ApiOperation("Atualiza uma consulta por ID")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Consulta atualizado"),
        @ApiResponse(code = 404, message = "Consulta não encontrado", response = Problem.class)
    })
    ConsultaModel atualizar(
        @ApiParam(value = "ID de uma consulta", example = "1", required = true)
            Long consultaId,

        @ApiParam(name = "corpo", value = "Representação de uma consulta com os novos dados",
            required = true)
            ConsultaInputModel consultaInput);

    @ApiOperation("Exclui uma consulta por ID")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Consulta excluída"),
        @ApiResponse(code = 404, message = "Consulta não encontrado", response = Problem.class)
    })
    void remover(
        @ApiParam(value = "ID de uma consulta", example = "1", required = true)
            Long consultaId);

}