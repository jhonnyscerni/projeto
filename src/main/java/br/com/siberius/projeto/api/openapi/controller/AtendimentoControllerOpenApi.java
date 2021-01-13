package br.com.siberius.projeto.api.openapi.controller;


import br.com.siberius.projeto.api.exceptionhandler.Problem;
import br.com.siberius.projeto.api.model.AtendimentoModel;
import br.com.siberius.projeto.api.model.input.AtendimentoInputModel;
import br.com.siberius.projeto.domain.repository.filter.AtendimentoFilter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Api(tags = "Atendimentos")
public interface AtendimentoControllerOpenApi {

    @ApiImplicitParams({
        @ApiImplicitParam(value = "Nomes das propriedades para filtrar na resposta, separados por vírgula",
            name = "campos", paramType = "query", type = "string")
    })
    @ApiOperation("Pesquisar os atendimentos")
    Page<AtendimentoModel> pesquisar(AtendimentoFilter filter, Pageable pageable);


    @ApiOperation("Busca um atendimento por ID")
    @ApiResponses({
        @ApiResponse(code = 400, message = "ID do atendimento inválido", response = Problem.class),
        @ApiResponse(code = 404, message = "Atendimento não encontrado", response = Problem.class)
    })
    AtendimentoModel buscar(
        @ApiParam(value = "ID do atendimento", example = "1", required = true)
            Long atendimentoId);


    @ApiOperation("Atualiza um atendimento por ID")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Atendimento atualizado"),
        @ApiResponse(code = 404, message = "Atendimento não encontrado", response = Problem.class)
    })
    AtendimentoModel atualizar(
        @ApiParam(value = "ID do atendimento", example = "1", required = true)
            Long atendimentoId,

        @ApiParam(name = "corpo", value = "Representação de um paciente com os novos dados",
            required = true)
            AtendimentoInputModel atendimentoInputModel);


    @ApiOperation("Exclui um atendimento por ID")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Atendimento excluído"),
        @ApiResponse(code = 404, message = "Atendimento não encontrada", response = Problem.class)
    })
    public void remover(
        @ApiParam(value = "ID de um atendimento", example = "1")
            Long atendimentoId);

}