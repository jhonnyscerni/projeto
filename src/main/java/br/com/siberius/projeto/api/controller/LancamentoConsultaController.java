package br.com.siberius.projeto.api.controller;

import br.com.siberius.projeto.api.assembler.LancamentoModelAssembler;
import br.com.siberius.projeto.api.assembler.disassembler.LancamentoInputModelDisassembler;
import br.com.siberius.projeto.api.model.LancamentoModel;
import br.com.siberius.projeto.api.openapi.controller.LancamentoConsultaControllerOpenApi;
import br.com.siberius.projeto.api.openapi.controller.LancamentoControllerOpenApi;
import br.com.siberius.projeto.core.security.resourceserver.CheckSecurity;
import br.com.siberius.projeto.domain.model.Lancamento;
import br.com.siberius.projeto.domain.repository.LancamentoRepository;
import br.com.siberius.projeto.domain.service.CategoriaLancamentoService;
import br.com.siberius.projeto.domain.service.FormaPagamentoService;
import br.com.siberius.projeto.domain.service.LancamentoService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/lancamentos-consulta", produces = MediaType.APPLICATION_JSON_VALUE)
public class LancamentoConsultaController implements LancamentoConsultaControllerOpenApi {

    @Autowired
    private LancamentoService lancamentoService;

    @Autowired
    private LancamentoModelAssembler assembler;

    @Autowired
    private LancamentoRepository lancamentoRepository;


    @CheckSecurity.Lancamentos.PodeConsultarLancamento
    @Override
    @GetMapping("/{consultaId}")
    public LancamentoModel buscarPorConsulta(@PathVariable Long consultaId) {
        Lancamento lancamento = lancamentoRepository.findByConsultaId(consultaId);
        return assembler.toModel(lancamento);
    }
}
