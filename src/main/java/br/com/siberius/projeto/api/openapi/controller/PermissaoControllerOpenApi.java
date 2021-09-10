package br.com.siberius.projeto.api.openapi.controller;

import br.com.siberius.projeto.api.exceptionhandler.Problem;
import br.com.siberius.projeto.api.model.PermissaoModel;
import br.com.siberius.projeto.api.model.input.PermissaoInputModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;

@Api(tags = "Permissões")
public interface PermissaoControllerOpenApi {

    @ApiOperation("Lista as permissões")
    List<PermissaoModel> listar();


    PermissaoModel buscar(
        @ApiParam(value = "ID da permissão", example = "1", required = true)
            Long permissaoId);

    @ApiOperation("Cadastra uma permissão")
    @ApiResponses({
        @ApiResponse(code = 201, message = "Permissão cadastrada"),
    })
    PermissaoModel adicionar(
        @ApiParam(name = "corpo", value = "Representação de um nova permissão", required = true)
            PermissaoInputModel permissaoInputModel);

    @ApiOperation("Atualiza uma permissão por ID")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Permissão atualizada"),
        @ApiResponse(code = 404, message = "Permissão não encontrada", response = Problem.class)
    })
    PermissaoModel atualizar(
        @ApiParam(value = "ID da permissão", example = "1", required = true)
            Long permissaoId,

        @ApiParam(name = "corpo", value = "Representação de uma permissão com os novos dados",
            required = true)
            PermissaoInputModel permissaoInputModel);

    @ApiOperation("Exclui uma permissão por ID")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Permissão excluída"),
        @ApiResponse(code = 404, message = "Permissão não encontrada", response = Problem.class)
    })
    public void remover(
        @ApiParam(value = "ID de uma Permissão", example = "1")
            Long permissaoId);

}