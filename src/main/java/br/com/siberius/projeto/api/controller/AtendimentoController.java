package br.com.siberius.projeto.api.controller;


import br.com.siberius.projeto.api.assembler.AtendimentoModelAssembler;
import br.com.siberius.projeto.api.assembler.disassembler.AtendimentoInputModelDisassembler;
import br.com.siberius.projeto.api.model.AtendimentoModel;
import br.com.siberius.projeto.api.model.input.AtendimentoInputModel;
import br.com.siberius.projeto.api.openapi.controller.AtendimentoControllerOpenApi;
import br.com.siberius.projeto.core.security.resourceserver.CheckSecurity;
import br.com.siberius.projeto.domain.model.Atendimento;
import br.com.siberius.projeto.domain.repository.AtendimentoRepository;
import br.com.siberius.projeto.domain.repository.filter.AtendimentoFilter;
import br.com.siberius.projeto.domain.service.AtendimentoService;
import br.com.siberius.projeto.infrastructure.repository.AtendimentoSpecs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/atendimentos", produces = MediaType.APPLICATION_JSON_VALUE)
public class AtendimentoController implements AtendimentoControllerOpenApi {

    @Autowired
    private AtendimentoService atendimentoService;

    @Autowired
    private AtendimentoRepository atendimentoRepository;

    @Autowired
    private AtendimentoModelAssembler assembler;

    @Autowired
    private AtendimentoInputModelDisassembler disassembler;

    @CheckSecurity.Atendimentos.PodeConsultarAtendimento
    @Override
    @GetMapping
    public Page<AtendimentoModel> pesquisar(AtendimentoFilter filter, @PageableDefault(size = 10) Pageable pageable) {

        Page<Atendimento> atendimentoRepositoryAll = atendimentoRepository.findAll(
                AtendimentoSpecs.usandoFiltro(filter), pageable);

        List<AtendimentoModel> atendimentoModelList = assembler
                .toCollectionModel(atendimentoRepositoryAll.getContent());

        return new PageImpl<>(
                atendimentoModelList, pageable, atendimentoRepositoryAll.getTotalElements());
    }

    @Override
    @GetMapping("/{atendimentoId}")
    public AtendimentoModel buscar(@PathVariable Long atendimentoId) {
        Atendimento atendimento = atendimentoService.buscarOuFalhar(atendimentoId);
        return assembler.toModel(atendimento);
    }

    @CheckSecurity.Atendimentos.PodeEditarAtendimento
    @Override
    @PutMapping("/{atendimentoId}")
    public AtendimentoModel atualizar(@PathVariable Long atendimentoId,
                                      @RequestBody @Valid AtendimentoInputModel atendimentoInputModel) {
        Atendimento atendimento = atendimentoService.buscarOuFalhar(atendimentoId);
        disassembler.copyToDomainObject(atendimentoInputModel, atendimento);

        return assembler.toModel(atendimentoService.salvar(atendimento));
    }

    @CheckSecurity.Atendimentos.PodeRemoverAtendimento
    @Override
    @DeleteMapping("/{atendimentoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long atendimentoId) {
        atendimentoService.excluir(atendimentoId);
    }
}
