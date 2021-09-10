package br.com.siberius.projeto.api.openapi.controller;

import br.com.siberius.projeto.api.model.CategoriaLancamentoModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;

@Api(tags = "Categorias de Lançamento")
public interface CategoriaLancamentoControllerOpenApi {

    @ApiOperation("Lista as Categorias de Lançamento")
    List<CategoriaLancamentoModel> listar();

}