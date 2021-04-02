package br.com.siberius.projeto.api.controller;

import br.com.siberius.projeto.api.assembler.LancamentoModelAssembler;
import br.com.siberius.projeto.api.assembler.disassembler.LancamentoInputModelDisassembler;
import br.com.siberius.projeto.api.model.LancamentoModel;
import br.com.siberius.projeto.api.model.input.LancamentoInputModel;
import br.com.siberius.projeto.api.openapi.controller.LancamentoControllerOpenApi;
import br.com.siberius.projeto.core.security.resourceserver.CheckSecurity;
import br.com.siberius.projeto.domain.model.Lancamento;
import br.com.siberius.projeto.domain.repository.LancamentoRepository;
import br.com.siberius.projeto.domain.repository.filter.LancamentoFilter;
import br.com.siberius.projeto.domain.service.CategoriaLancamentoService;
import br.com.siberius.projeto.domain.service.FormaPagamentoService;
import br.com.siberius.projeto.domain.service.LancamentoService;
import br.com.siberius.projeto.infrastructure.repository.LancamentoSpecs;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/lancamentos", produces = MediaType.APPLICATION_JSON_VALUE)
public class LancamentoController implements LancamentoControllerOpenApi {

    @Autowired
    private LancamentoRepository lancamentoRepository;

    @Autowired
    private LancamentoService lancamentoService;

    @Autowired
    private LancamentoModelAssembler assembler;

    @Autowired
    private LancamentoInputModelDisassembler disassembler;

    @Autowired
    private FormaPagamentoService formaPagamentoService;

    @Autowired
    private CategoriaLancamentoService categoriaLancamentoService;

    @CheckSecurity.Lancamentos.PodeConsultarLancamento
    @Override
    @GetMapping
    public Page<LancamentoModel> pesquisar(LancamentoFilter filter, @PageableDefault(size = 10) Pageable pageable) {
        Page<Lancamento> lancamentoRepositoryAll = lancamentoRepository.findAll(
            LancamentoSpecs.usandoFiltro(filter), pageable);

        List<LancamentoModel> lancamentoModelList = assembler
            .toCollectionModel(lancamentoRepositoryAll.getContent());

        return new PageImpl<>(
            lancamentoModelList, pageable, lancamentoRepositoryAll.getTotalElements());
    }

    @CheckSecurity.Lancamentos.PodeConsultarLancamento
    @Override
    @GetMapping("/{lancamentoId}")
    public LancamentoModel buscar(@PathVariable Long lancamentoId) {
        Lancamento lancamento = lancamentoService.buscarOuFalhar(lancamentoId);
        return assembler.toModel(lancamento);
    }

    @CheckSecurity.Lancamentos.PodeCadastrarLancamento
    @Override
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public LancamentoModel adicionar(@RequestBody @Valid LancamentoInputModel lancamentoInputModel) {
        Lancamento lancamento = disassembler.toDomainObject(lancamentoInputModel);
        return assembler.toModel(lancamentoService.salvar(lancamento));
    }

    @CheckSecurity.Lancamentos.PodeEditarLancamento
    @Override
    @PutMapping("/{lancamentoId}")
    public LancamentoModel atualizar(@PathVariable Long lancamentoId,
        @RequestBody @Valid LancamentoInputModel lancamentoInputModel) {
        Lancamento lancamento = lancamentoService.buscarOuFalhar(lancamentoId);
        disassembler.copyToDomainObject(lancamentoInputModel, lancamento);
        return assembler.toModel(lancamentoService.salvar(lancamento));
    }

    @CheckSecurity.Lancamentos.PodeRemoverLancamento
    @Override
    @DeleteMapping("/{lancamentoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long lancamentoId) {
        lancamentoService.excluir(lancamentoId);
    }

}
