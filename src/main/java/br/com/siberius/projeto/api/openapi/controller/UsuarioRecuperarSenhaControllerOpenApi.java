package br.com.siberius.projeto.api.openapi.controller;

import br.com.siberius.projeto.api.exceptionhandler.Problem;
import br.com.siberius.projeto.api.model.UsuarioModel;
import br.com.siberius.projeto.api.model.input.UsuarioInputComSenhaModel;
import br.com.siberius.projeto.api.model.input.UsuarioInputRecuperarSenhaModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Recuperar Senha E-mail")
public interface UsuarioRecuperarSenhaControllerOpenApi {

    @ApiOperation("Gerar nova senha de acesso para email cadastrado")
    @ApiResponses({
        @ApiResponse(code = 201, message = "Nova senha gerada com sucesso"),
    })
    UsuarioModel adicionar(
        @ApiParam(name = "corpo", value = "E-mail para geração da nova senha de acesso para email", required = true)
            UsuarioInputRecuperarSenhaModel usuarioInput);

}
