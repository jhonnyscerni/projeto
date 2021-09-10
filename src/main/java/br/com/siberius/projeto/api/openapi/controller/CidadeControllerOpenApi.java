package br.com.siberius.projeto.api.openapi.controller;

import br.com.siberius.projeto.api.exceptionhandler.Problem;
import br.com.siberius.projeto.api.model.CidadeModel;
import io.swagger.annotations.*;

import java.util.List;

@Api(tags = "Cidades")
public interface CidadeControllerOpenApi {

    @ApiOperation("Lista as cidades")
    List<CidadeModel> listar();

    @ApiOperation("Busca uma Lista de cidades por ID de Estado")
    @ApiResponses({
            @ApiResponse(code = 400, message = "ID da estado inválido", response = Problem.class),
            @ApiResponse(code = 404, message = "Estado não encontrado", response = Problem.class)
    })
    List<CidadeModel> buscarPorEstadoId(
            @ApiParam(value = "ID de um estado", example = "1", required = true)
                    Long estadoId);

    @ApiOperation("Busca uma cidade por ID")
    @ApiResponses({
            @ApiResponse(code = 400, message = "ID da cidade inválido", response = Problem.class),
            @ApiResponse(code = 404, message = "Cidade não encontrado", response = Problem.class)
    })
    CidadeModel buscar(
            @ApiParam(value = "ID de uma cidade", example = "1", required = true)
                    Long ciadeId);

    @ApiOperation("Busca uma cidade por Nome e Id Estado")
    @ApiResponses({
            @ApiResponse(code = 400, message = "Nome da cidade ou Id do Estado inválido", response = Problem.class),
            @ApiResponse(code = 404, message = "Cidade não encontrado", response = Problem.class)
    })
    CidadeModel buscarPorNomeESiglaEstado(
            @ApiParam(value = "Nome de uma cidade", example = "Assis Brasil", required = true)
                    String nomeCidade,
            @ApiParam(value = "sigla do Estado", example = "SP", required = true)
                    String sigla);


}