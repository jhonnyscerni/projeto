package br.com.siberius.projeto.api.controller;

import br.com.siberius.projeto.api.assembler.CidadeModelAssembler;
import br.com.siberius.projeto.api.model.CidadeModel;
import br.com.siberius.projeto.api.openapi.controller.CidadeControllerOpenApi;
import br.com.siberius.projeto.core.security.resourceserver.CheckSecurity;
import br.com.siberius.projeto.domain.model.Estado;
import br.com.siberius.projeto.domain.repository.CidadeRepository;
import br.com.siberius.projeto.domain.service.CidadeService;
import br.com.siberius.projeto.domain.service.EstadoService;
import br.com.siberius.projeto.infrastructure.repository.CidadeSpecs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/cidades", produces = MediaType.APPLICATION_JSON_VALUE)
public class CidadeController implements CidadeControllerOpenApi {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private CidadeModelAssembler assembler;

    @Autowired
    private CidadeService cidadeService;

    @CheckSecurity.Cidades.PodeConsultarCidade
    @Override
    @GetMapping
    public List<CidadeModel> listar() {
        return assembler.toCollectionModel(cidadeRepository.findAll());
    }

    @CheckSecurity.Cidades.PodeConsultarCidade
    @Override
    @GetMapping("/estado/{estadoId}")
    public List<CidadeModel> buscarPorEstadoId(@PathVariable Long estadoId) {
        return assembler.toCollectionModel(cidadeRepository.findAll(
                CidadeSpecs.usandoFiltroEstadoId(estadoId)
        ));
    }

    @CheckSecurity.Cidades.PodeConsultarCidade
    @Override
    @GetMapping("/{cidadeId}")
    public CidadeModel buscar(@PathVariable Long cidadeId) {
        return assembler.toModel(cidadeService.buscarOuFalhar(cidadeId));
    }

    @CheckSecurity.Cidades.PodeConsultarCidade
    @Override
    @GetMapping("/nome/{nomeCidade}/sigla/{sigla}")
    public CidadeModel buscarPorNomeESiglaEstado(@PathVariable String nomeCidade, @PathVariable String sigla) {
        return assembler.toModel(
               cidadeService.buscarPorNomeESiglaEstado(nomeCidade, sigla));
    }
}
