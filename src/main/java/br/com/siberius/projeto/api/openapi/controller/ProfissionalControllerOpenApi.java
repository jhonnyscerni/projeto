package br.com.siberius.projeto.api.openapi.controller;


import br.com.siberius.projeto.api.exceptionhandler.Problem;
import br.com.siberius.projeto.api.model.PacienteModel;
import br.com.siberius.projeto.api.model.ProfissionalModel;
import br.com.siberius.projeto.api.model.UsuarioModel;
import br.com.siberius.projeto.api.model.input.ProfissionalInputComSenhaModel;
import br.com.siberius.projeto.api.model.input.SenhaInputModel;
import br.com.siberius.projeto.api.model.input.UsuarioInputComSenhaModel;
import br.com.siberius.projeto.domain.repository.filter.PacienteFilter;
import br.com.siberius.projeto.domain.repository.filter.ProfissionalFilter;
import br.com.siberius.projeto.domain.repository.filter.UsuarioFilter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Api(tags = "Profissionais")
public interface ProfissionalControllerOpenApi {

    @ApiImplicitParams({
        @ApiImplicitParam(value = "Nomes das propriedades para filtrar na resposta, separados por vírgula",
            name = "campos", paramType = "query", type = "string")
    })
    @ApiOperation("Pesquisar os profissionais")
    Page<ProfissionalModel> pesquisar(ProfissionalFilter filter, Pageable pageable);

    @ApiImplicitParams({
            @ApiImplicitParam(value = "Nomes das propriedades para filtrar na resposta, separados por vírgula",
                    name = "campos", paramType = "query", type = "string")
    })
    @ApiOperation("Pesquisar os profissionais")
    List<ProfissionalModel> pesquisar(ProfissionalFilter filter);

    @ApiOperation("Busca um profissional por ID")
    @ApiResponses({
        @ApiResponse(code = 400, message = "ID do profissional inválido", response = Problem.class),
        @ApiResponse(code = 404, message = "Profissional não encontrado", response = Problem.class)
    })
    ProfissionalModel buscar(
        @ApiParam(value = "ID do profissional", example = "1", required = true)
            Long profissionalId);

    @ApiOperation("Cadastra um Profissional")
    @ApiResponses({
        @ApiResponse(code = 201, message = "Profissional cadastrado"),
    })
    ProfissionalModel adicionar(
        @ApiParam(name = "corpo", value = "Representação de um novo profissional", required = true)
            ProfissionalInputComSenhaModel profissionalInputComSenhaModel);

    @ApiOperation("Atualiza um profissional por ID")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Profissional atualizado"),
        @ApiResponse(code = 404, message = "Profissional não encontrado", response = Problem.class)
    })

    ProfissionalModel adicionarComum(
        @ApiParam(name = "corpo", value = "Representação de um novo profissional", required = true)
            ProfissionalInputComSenhaModel profissionalInputComSenhaModel);

    @ApiOperation("Atualiza um profissional por ID")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Profissional atualizado"),
        @ApiResponse(code = 404, message = "Profissional não encontrado", response = Problem.class)
    })

    ProfissionalModel atualizar(
        @ApiParam(value = "ID do profissional", example = "1", required = true)
            Long profissionalId,

        @ApiParam(name = "corpo", value = "Representação de um profissional com os novos dados",
            required = true)
            ProfissionalInputComSenhaModel profissionalInputComSenhaModel);

    @ApiOperation("Atualiza a senha de um profissional")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Senha alterada com sucesso"),
        @ApiResponse(code = 404, message = "Profissional não encontrado", response = Problem.class)
    })
    void alterarSenha(
        @ApiParam(value = "ID do profissional", example = "1", required = true)
            Long profissionalId,

        @ApiParam(name = "corpo", value = "Representação de uma nova senha",
            required = true)
            SenhaInputModel senha);

    @ApiOperation("Exclui um profissional por ID")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Profissional excluído"),
        @ApiResponse(code = 404, message = "Profissional não encontrada", response = Problem.class)
    })
    public void remover(
        @ApiParam(value = "ID de um profissional", example = "1")
            Long profissionalId);

}