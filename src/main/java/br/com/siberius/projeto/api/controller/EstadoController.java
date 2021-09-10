package br.com.siberius.projeto.api.controller;

import br.com.siberius.projeto.api.assembler.EstadoModelAssembler;
import br.com.siberius.projeto.api.model.CidadeModel;
import br.com.siberius.projeto.api.model.EstadoModel;
import br.com.siberius.projeto.api.openapi.controller.EstadoControllerOpenApi;
import br.com.siberius.projeto.core.security.resourceserver.CheckSecurity;
import br.com.siberius.projeto.domain.repository.EstadoRepository;
import br.com.siberius.projeto.domain.service.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/estados", produces = MediaType.APPLICATION_JSON_VALUE)
public class EstadoController implements EstadoControllerOpenApi {

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private EstadoModelAssembler assembler;

    @Autowired
    private EstadoService estadoService;

    @CheckSecurity.Estados.PodeConsultarEstado
    @Override
    @GetMapping
    public List<EstadoModel> listar() {
        return assembler.toCollectionModel(estadoRepository.findAll());
    }

    //@CheckSecurity.Estados.PodeConsultarEstado
    @Override
    @GetMapping("/{estadoId}")
    public EstadoModel buscar(@PathVariable Long estadoId) {
        return assembler.toModel(estadoService.buscarOuFalhar(estadoId));
    }

    //@CheckSecurity.Estados.PodeConsultarEstado
    @Override
    @GetMapping("/sigla/{siglaEstado}")
    public EstadoModel buscarPorSigla(@PathVariable String siglaEstado) {
        return assembler.toModel(estadoRepository.findBySigla(siglaEstado));
    }
}
