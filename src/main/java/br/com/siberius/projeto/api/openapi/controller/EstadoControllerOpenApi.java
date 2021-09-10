package br.com.siberius.projeto.api.openapi.controller;

import br.com.siberius.projeto.api.exceptionhandler.Problem;
import br.com.siberius.projeto.api.model.EstadoModel;
import io.swagger.annotations.*;

import java.util.List;

@Api(tags = "Estados")
public interface EstadoControllerOpenApi {

    @ApiOperation("Lista as estados")
    List<EstadoModel> listar();

    @ApiOperation("Busca um estado por ID")
    @ApiResponses({
            @ApiResponse(code = 400, message = "ID do estado inválido", response = Problem.class),
            @ApiResponse(code = 404, message = "Estado não encontrado", response = Problem.class)
    })
    EstadoModel buscar(
            @ApiParam(value = "ID de um estado", example = "1", required = true)
                    Long estadoId);

    @ApiOperation("Busca um estado por Sigla")
    @ApiResponses({
            @ApiResponse(code = 400, message = "Sigla do estado inválido", response = Problem.class),
            @ApiResponse(code = 404, message = "Estado não encontrado", response = Problem.class)
    })
    EstadoModel buscarPorSigla(
            @ApiParam(value = "Sigla de um estado", example = "SP", required = true)
                    String siglaEstado);

}