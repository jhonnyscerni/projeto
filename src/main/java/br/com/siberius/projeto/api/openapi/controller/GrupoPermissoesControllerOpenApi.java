package br.com.siberius.projeto.api.openapi.controller;

import br.com.siberius.projeto.api.exceptionhandler.Problem;
import br.com.siberius.projeto.api.model.GrupoModel;
import br.com.siberius.projeto.api.model.PermissaoModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;

@Api(tags = "Grupos")
public interface GrupoPermissoesControllerOpenApi {

    @ApiOperation("Lista os permissões associados a um grupo")
    @ApiResponses({
        @ApiResponse(code = 404, message = "Grupo não encontrado", response = Problem.class)
    })
    List<PermissaoModel> listar(
        @ApiParam(value = "ID da grupo", example = "1", required = true)
            Long grupoId);

    @ApiOperation("Desassociação da permissão com grupo")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Desassociação realizada com sucesso"),
        @ApiResponse(code = 404, message = "Grupo ou permissão não encontrado",
            response = Problem.class)
    })
    void desassociar(
        @ApiParam(value = "ID do Grupo", example = "1", required = true)
            Long grupoId,

        @ApiParam(value = "ID da Permissão", example = "1", required = true)
            Long permissaoId);

    @ApiOperation("Associação da permissão com grupo")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Associação realizada com sucesso"),
        @ApiResponse(code = 404, message = "Grupo ou permissão não encontrado",
            response = Problem.class)
    })
    void associar(
        @ApiParam(value = "ID do Grupo", example = "1", required = true)
            Long grupoId,

        @ApiParam(value = "ID da Permissão", example = "1", required = true)
            Long permissaoId);
}