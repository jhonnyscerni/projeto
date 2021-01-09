package br.com.siberius.projeto.api.controller;

import br.com.siberius.projeto.api.assembler.ConsultaModelAssembler;
import br.com.siberius.projeto.api.assembler.disassembler.ConsultaInputModelDisassembler;
import br.com.siberius.projeto.api.model.ConsultaModel;
import br.com.siberius.projeto.api.model.PacienteModel;
import br.com.siberius.projeto.api.model.input.ConsultaInputModel;
import br.com.siberius.projeto.api.openapi.controller.ConsultaControllerOpenApi;
import br.com.siberius.projeto.domain.model.Consulta;
import br.com.siberius.projeto.domain.model.Paciente;
import br.com.siberius.projeto.domain.repository.ConsultaRepository;
import br.com.siberius.projeto.domain.repository.filter.ConsultaFilter;
import br.com.siberius.projeto.domain.repository.filter.PacienteFilter;
import br.com.siberius.projeto.domain.service.ConsultaService;
import br.com.siberius.projeto.infrastructure.repository.ConsultaSpecs;
import br.com.siberius.projeto.infrastructure.repository.PacienteSpecs;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/{consultaId}")
    @Override
    public ConsultaModel buscar(@PathVariable Long consultaId) {
        return assembler.toModel(consultaService.buscarOuFalhar(consultaId));
    }

    @Override
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ConsultaModel adicionar(@RequestBody @Valid ConsultaInputModel consultaInputModel) {
        Consulta consulta = disassembler.toDomainObject(consultaInputModel);
        return assembler.toModel(consultaService.salvar(consulta));
    }

    @Override
    @PutMapping("/{consultaId}")
    public ConsultaModel atualizar(@PathVariable Long consultaId, @RequestBody @Valid ConsultaInputModel consultaInput) {
        Consulta consulta = consultaService.buscarOuFalhar(consultaId);
        Consulta consultaAlterada =  disassembler.toDomainObjectConsulta(consultaInput);
        consultaAlterada.getProfissional().setDataCadastro(consulta.getProfissional().getDataCadastro());
        consultaAlterada.getPaciente().setDataCadastro(consulta.getPaciente().getDataCadastro());
        consultaAlterada = consultaService.salvar(consultaAlterada);
        return assembler.toModel(consultaAlterada);
    }

    @Override
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(Long consultaId) {
        consultaService.excluir(consultaId);
    }
}
