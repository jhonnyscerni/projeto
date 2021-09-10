package br.com.siberius.projeto.api.controller;

import br.com.siberius.projeto.api.assembler.CategoriaLancamentoModelAssembler;
import br.com.siberius.projeto.api.model.CategoriaLancamentoModel;
import br.com.siberius.projeto.api.openapi.controller.CategoriaLancamentoControllerOpenApi;
import br.com.siberius.projeto.domain.repository.CategoriaLancamentoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/categoria-lancamento", produces = MediaType.APPLICATION_JSON_VALUE)
public class CategoriaLancamentoController implements CategoriaLancamentoControllerOpenApi {

    @Autowired
    private CategoriaLancamentoRepository categoriaLancamentoRepository;

    @Autowired
    private CategoriaLancamentoModelAssembler assembler;

    @Override
    @GetMapping
    public List<CategoriaLancamentoModel> listar() {
        return assembler.toCollectionModel(categoriaLancamentoRepository.findAll());
    }

}