package br.com.siberius.projeto.api.openapi.controller;

import br.com.siberius.projeto.api.exceptionhandler.Problem;
import br.com.siberius.projeto.api.model.UsuarioModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.servlet.ModelAndView;

@Api(tags = "Confirmar Registro E-mail")
public interface UsuarioValidarTokenControllerOpenApi {

    @ApiOperation("Busca o registro no sistema para confirmar acesso")
    @ApiResponses({
        @ApiResponse(code = 400, message = "Token de registro do usuário inválido", response = Problem.class),
        @ApiResponse(code = 404, message = "Token de registro do Usuário não encontrado", response = Problem.class)
    })
    ModelAndView confirmRegistration(
        @ApiParam(value = "Token de Validação do Registro", example = "1", required = true)
            String token);
}
