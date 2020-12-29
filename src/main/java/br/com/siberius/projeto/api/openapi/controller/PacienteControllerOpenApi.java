package br.com.siberius.projeto.api.openapi.controller;


import br.com.siberius.projeto.api.exceptionhandler.Problem;
import br.com.siberius.projeto.api.model.PacienteModel;
import br.com.siberius.projeto.api.model.ProfissionalModel;
import br.com.siberius.projeto.api.model.input.PacienteInputComSenhaModel;
import br.com.siberius.projeto.api.model.input.ProfissionalInputComSenhaModel;
import br.com.siberius.projeto.api.model.input.SenhaInputModel;
import br.com.siberius.projeto.domain.repository.filter.PacienteFilter;
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

@Api(tags = "Pacientes")
public interface PacienteControllerOpenApi {

    @ApiImplicitParams({
        @ApiImplicitParam(value = "Nomes das propriedades para filtrar na resposta, separados por vírgula",
            name = "campos", paramType = "query", type = "string")
    })
    @ApiOperation("Pesquisar os pacientes")
    Page<PacienteModel> pesquisar(PacienteFilter filter, Pageable pageable);

    @ApiOperation("Busca um paciente por ID")
    @ApiResponses({
        @ApiResponse(code = 400, message = "ID do paciente inválido", response = Problem.class),
        @ApiResponse(code = 404, message = "Paciente não encontrado", response = Problem.class)
    })
    PacienteModel buscar(
        @ApiParam(value = "ID do paciente", example = "1", required = true)
            Long pacienteId);

    @ApiOperation("Cadastra um Paciente")
    @ApiResponses({
        @ApiResponse(code = 201, message = "Paciente cadastrado"),
    })
    PacienteModel adicionar(
        @ApiParam(name = "corpo", value = "Representação de um novo paciente", required = true)
            PacienteInputComSenhaModel pacienteInputComSenhaModel);

    @ApiOperation("Atualiza um paciente por ID")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Paciente atualizado"),
        @ApiResponse(code = 404, message = "Paciente não encontrado", response = Problem.class)
    })
    PacienteModel atualizar(
        @ApiParam(value = "ID do paciente", example = "1", required = true)
            Long pacienteId,

        @ApiParam(name = "corpo", value = "Representação de um paciente com os novos dados",
            required = true)
            PacienteInputComSenhaModel pacienteInputComSenhaModel);

    @ApiOperation("Atualiza a senha de um paciente")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Senha alterada com sucesso"),
        @ApiResponse(code = 404, message = "Paciente não encontrado", response = Problem.class)
    })
    void alterarSenha(
        @ApiParam(value = "ID do paciente", example = "1", required = true)
            Long pacienteId,

        @ApiParam(name = "corpo", value = "Representação de uma nova senha",
            required = true)
            SenhaInputModel senha);

    @ApiOperation("Exclui um paciente por ID")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Paciente excluído"),
        @ApiResponse(code = 404, message = "Paciente não encontrada", response = Problem.class)
    })
    public void remover(
        @ApiParam(value = "ID de um paciente", example = "1")
            Long pacienteId);

}