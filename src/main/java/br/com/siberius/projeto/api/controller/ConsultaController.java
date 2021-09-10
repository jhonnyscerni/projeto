package br.com.siberius.projeto.api.controller;

import br.com.siberius.projeto.api.assembler.ConsultaModelAssembler;
import br.com.siberius.projeto.api.assembler.disassembler.ConsultaInputModelDisassembler;
import br.com.siberius.projeto.api.model.ConsultaModel;
import br.com.siberius.projeto.api.model.input.ConsultaInputModel;
import br.com.siberius.projeto.api.openapi.controller.ConsultaControllerOpenApi;
import br.com.siberius.projeto.core.security.resourceserver.CheckSecurity;
import br.com.siberius.projeto.domain.model.Consulta;
import br.com.siberius.projeto.domain.model.Paciente;
import br.com.siberius.projeto.domain.repository.ConsultaRepository;
import br.com.siberius.projeto.domain.repository.filter.ConsultaFilter;
import br.com.siberius.projeto.domain.service.ConsultaService;
import br.com.siberius.projeto.infrastructure.repository.ConsultaSpecs;
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
@RequestMapping(path = "/consultas", produces = MediaType.APPLICATION_JSON_VALUE)
public class ConsultaController implements ConsultaControllerOpenApi {

    @Autowired
    private ConsultaModelAssembler assembler;

    @Autowired
    private ConsultaInputModelDisassembler disassembler;

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private ConsultaService consultaService;

    @CheckSecurity.Consultas.PodeConsultarConsulta
    @Override
    @GetMapping
    public Page<ConsultaModel> pesquisar(ConsultaFilter filter, @PageableDefault(size = 10) Pageable pageable) {
        Page<Consulta> consultaRepositoryAll = consultaRepository.findAll(
            ConsultaSpecs.usandoFiltro(filter), pageable);

        List<ConsultaModel> consultaModelList = assembler
            .toCollectionModel(consultaRepositoryAll.getContent());

        return new PageImpl<>(
            consultaModelList, pageable, consultaRepositoryAll.getTotalElements());
    }

    @CheckSecurity.Consultas.PodeConsultarConsulta
    @Override
    @GetMapping("/lista")
    public List<ConsultaModel> pesquisar(ConsultaFilter filter) {

        List<Consulta> consultaRepositoryAll = consultaRepository.findAll(
            ConsultaSpecs.usandoFiltro(filter));

        return assembler.toCollectionModel(consultaRepositoryAll);
    }
    //@CheckSecurity.Consultas.PodeConsultarConsulta
    @GetMapping("/{consultaId}")
    @Override
    public ConsultaModel buscar(@PathVariable Long consultaId) {
        return assembler.toModel(consultaService.buscarOuFalhar(consultaId));
    }

    @CheckSecurity.Consultas.PodeCadastrarConsulta
    @Override
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ConsultaModel adicionar(@RequestBody @Valid ConsultaInputModel consultaInputModel) {
        Consulta consulta = disassembler.toDomainObject(consultaInputModel);
        return assembler.toModel(consultaService.salvar(consulta));
    }

    @CheckSecurity.Consultas.PodeEditarConsulta
    @Override
    @PutMapping("/{consultaId}")
    public ConsultaModel atualizar(@PathVariable Long consultaId, @RequestBody @Valid ConsultaInputModel consultaInput) {
        Consulta consulta = consultaService.buscarOuFalhar(consultaId);
        Consulta consultaAlterada =  disassembler.toDomainObjectConsulta(consultaInput);
        consultaAlterada.getProfissional().setDataCadastro(consulta.getProfissional().getDataCadastro());
        if (consultaAlterada.getPaciente() == null) {
            consultaAlterada.setPaciente(new Paciente());
            consultaAlterada.getPaciente().setId(consulta.getPaciente().getId());
            consultaAlterada.getPaciente().setDataCadastro(consulta.getPaciente().getDataCadastro());
        }
        consultaAlterada = consultaService.salvar(consultaAlterada);
        return assembler.toModel(consultaAlterada);
    }

    @CheckSecurity.Consultas.PodeRemoverConsulta
    @Override
    @DeleteMapping("/{consultaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long consultaId) {
        consultaService.excluir(consultaId);
    }
}
