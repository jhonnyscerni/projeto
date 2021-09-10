package br.com.siberius.projeto.api.openapi.controller;


import br.com.siberius.projeto.api.exceptionhandler.Problem;
import br.com.siberius.projeto.api.model.ClinicaModel;
import br.com.siberius.projeto.api.model.ProfissionalModel;
import br.com.siberius.projeto.api.model.input.ClinicaInputComSenhaModel;
import br.com.siberius.projeto.api.model.input.ProfissionalInputComSenhaModel;
import br.com.siberius.projeto.api.model.input.SenhaInputModel;
import br.com.siberius.projeto.domain.repository.filter.ClinicaFilter;
import br.com.siberius.projeto.domain.repository.filter.ProfissionalFilter;
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

@Api(tags = "Clinicas")
public interface ClinicaControllerOpenApi {

    @ApiImplicitParams({
        @ApiImplicitParam(value = "Nomes das propriedades para filtrar na resposta, separados por vírgula",
            name = "campos", paramType = "query", type = "string")
    })
    @ApiOperation("Pesquisar as Clinicas")
    Page<ClinicaModel> pesquisar(ClinicaFilter filter, Pageable pageable);

    @ApiImplicitParams({
            @ApiImplicitParam(value = "Nomes das propriedades para filtrar na resposta, separados por vírgula",
                    name = "campos", paramType = "query", type = "string")
    })
    @ApiOperation("Pesquisar os Clinicas Lista")
    List<ClinicaModel> pesquisar(ClinicaFilter filter);

    @ApiOperation("Busca uma clinica por ID")
    @ApiResponses({
        @ApiResponse(code = 400, message = "ID da clinica inválido", response = Problem.class),
        @ApiResponse(code = 404, message = "Clinica não encontrado", response = Problem.class)
    })
    ClinicaModel buscar(
        @ApiParam(value = "ID da clinica", example = "1", required = true)
            Long clinicaId);

    @ApiOperation("Cadastra um Clinica")
    @ApiResponses({
        @ApiResponse(code = 201, message = "Clinica cadastrado"),
    })
    ClinicaModel adicionar(
        @ApiParam(name = "corpo", value = "Representação de uma nova clinica", required = true)
            ClinicaInputComSenhaModel clinicaInputComSenhaModel);

    @ApiOperation("Atualiza uma clinica por ID")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Clinica atualizado"),
        @ApiResponse(code = 404, message = "Clinica não encontrado", response = Problem.class)
    })

    ClinicaModel adicionarComum(
        @ApiParam(name = "corpo", value = "Representação de uma nova clinica", required = true)
            ClinicaInputComSenhaModel clinicaInputComSenhaModel);

    @ApiOperation("Atualiza uma clinica por ID")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Clinica atualizado"),
        @ApiResponse(code = 404, message = "Clinica não encontrado", response = Problem.class)
    })

    ClinicaModel atualizar(
        @ApiParam(value = "ID da clinica", example = "1", required = true)
            Long clinicaId,

        @ApiParam(name = "corpo", value = "Representação de uma clinica com os novos dados",
            required = true)
            ClinicaInputComSenhaModel clinicaInputComSenhaModel);

    @ApiOperation("Atualiza a senha de uma clinica")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Senha alterada com sucesso"),
        @ApiResponse(code = 404, message = "Clinica não encontrado", response = Problem.class)
    })
    void alterarSenha(
        @ApiParam(value = "ID da clinica", example = "1", required = true)
            Long clinicaId,

        @ApiParam(name = "corpo", value = "Representação de uma nova senha",
            required = true)
            SenhaInputModel senha);

    @ApiOperation("Exclui uma clinica por ID")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Clinica excluído"),
        @ApiResponse(code = 404, message = "Clinica não encontrada", response = Problem.class)
    })
    public void remover(
        @ApiParam(value = "ID de uma clinica", example = "1")
            Long clinicaId);

}