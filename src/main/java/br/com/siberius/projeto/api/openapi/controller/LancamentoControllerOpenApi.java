package br.com.siberius.projeto.api.openapi.controller;


import br.com.siberius.projeto.api.exceptionhandler.Problem;
import br.com.siberius.projeto.api.model.LancamentoModel;
import br.com.siberius.projeto.api.model.input.LancamentoInputModel;
import br.com.siberius.projeto.domain.repository.filter.LancamentoFilter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Api(tags = "Lançamentos Financeiros")
public interface LancamentoControllerOpenApi {

    @ApiImplicitParams({
        @ApiImplicitParam(value = "Nomes das propriedades para filtrar na resposta, separados por vírgula",
            name = "campos", paramType = "query", type = "string")
    })
    @ApiOperation("Pesquisar os lancamentos")
    Page<LancamentoModel> pesquisar(LancamentoFilter filter, Pageable pageable);

    @ApiOperation("Busca um usuário por ID")
    @ApiResponses({
        @ApiResponse(code = 400, message = "ID do lançamento inválido", response = Problem.class),
        @ApiResponse(code = 404, message = "Lançamento não encontrado", response = Problem.class)
    })
    LancamentoModel buscar(
        @ApiParam(value = "ID do lançamento", example = "1", required = true)
            Long lancamentoId);

    @ApiOperation("Cadastra um Lançnto")
    @ApiResponses({
        @ApiResponse(code = 201, message = "Lançamento cadastrado"),
    })
    LancamentoModel adicionar(
        @ApiParam(name = "corpo", value = "Representação de um novo usuário", required = true)
            LancamentoInputModel lancamentoInputModel);

    @ApiOperation("Atualiza um lançamento por ID")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Usuário atualizado"),
        @ApiResponse(code = 404, message = "Usuário não encontrado", response = Problem.class)
    })
    LancamentoModel atualizar(
        @ApiParam(value = "ID do lançamento", example = "1", required = true)
            Long lancamentoId,

        @ApiParam(name = "corpo", value = "Representação de um lançamento com os novos dados",
            required = true)
            LancamentoInputModel lancamentoInputModel);


    @ApiOperation("Exclui um lançamento por ID")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Lançamento excluído"),
        @ApiResponse(code = 404, message = "Lançamento não encontrada", response = Problem.class)
    })
    public void remover(
        @ApiParam(value = "ID de um Lançamento", example = "1")
            Long lancamentoId);

}