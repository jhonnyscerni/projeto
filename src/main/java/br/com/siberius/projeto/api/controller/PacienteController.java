package br.com.siberius.projeto.api.controller;

import br.com.siberius.projeto.api.assembler.GrupoModelAssembler;
import br.com.siberius.projeto.api.assembler.PacienteModelAssembler;
import br.com.siberius.projeto.api.assembler.ProfissionalModelAssembler;
import br.com.siberius.projeto.api.assembler.disassembler.PacienteInputModelDisassembler;
import br.com.siberius.projeto.api.assembler.disassembler.ProfissionalInputModelDisassembler;
import br.com.siberius.projeto.api.event.RegistroCompletoEvent;
import br.com.siberius.projeto.api.model.GrupoModel;
import br.com.siberius.projeto.api.model.PacienteModel;
import br.com.siberius.projeto.api.model.ProfissionalModel;
import br.com.siberius.projeto.api.model.input.PacienteInputComSenhaModel;
import br.com.siberius.projeto.api.model.input.ProfissionalInputComSenhaModel;
import br.com.siberius.projeto.api.model.input.SenhaInputModel;
import br.com.siberius.projeto.api.openapi.controller.PacienteControllerOpenApi;
import br.com.siberius.projeto.api.openapi.controller.ProfissionalControllerOpenApi;
import br.com.siberius.projeto.core.security.resourceserver.CheckSecurity;
import br.com.siberius.projeto.core.security.resourceserver.ProjetoSecurity;
import br.com.siberius.projeto.domain.model.Grupo;
import br.com.siberius.projeto.domain.model.Paciente;
import br.com.siberius.projeto.domain.model.Profissional;
import br.com.siberius.projeto.domain.model.Usuario;
import br.com.siberius.projeto.domain.repository.PacienteRepository;
import br.com.siberius.projeto.domain.repository.filter.PacienteFilter;
import br.com.siberius.projeto.domain.repository.filter.ProfissionalFilter;
import br.com.siberius.projeto.domain.service.GrupoService;
import br.com.siberius.projeto.domain.service.PacienteService;
import br.com.siberius.projeto.infrastructure.repository.PacienteSpecs;
import br.com.siberius.projeto.infrastructure.repository.ProfissionalSpecs;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
@RequestMapping(path = "/pacientes", produces = MediaType.APPLICATION_JSON_VALUE)
public class PacienteController implements PacienteControllerOpenApi {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private GrupoService grupoService;

    @Autowired
    private PacienteModelAssembler assembler;

    @Autowired
    private GrupoModelAssembler assemblerGrupo;

    @Autowired
    private PacienteInputModelDisassembler disassembler;

    @Autowired
    ApplicationEventPublisher eventPublisher;

    @CheckSecurity.Pacientes.PodeConsultarPaciente
    @Override
    @GetMapping
    public Page<PacienteModel> pesquisar(PacienteFilter filter, @PageableDefault(size = 10) Pageable pageable) {

        Page<Paciente> pacienteRepositoryAll = pacienteRepository.findAll(
            PacienteSpecs.usandoFiltro(filter), pageable);

        List<PacienteModel> pacienteModelList = assembler
            .toCollectionModel(pacienteRepositoryAll.getContent());

        return new PageImpl<>(
            pacienteModelList, pageable, pacienteRepositoryAll.getTotalElements());
    }

    @CheckSecurity.Pacientes.PodeBuscar
    @Override
    @GetMapping("/{pacienteId}")
    public PacienteModel buscar(@PathVariable Long pacienteId) {
        Paciente paciente = pacienteService.buscarOuFalhar(pacienteId);
        return assembler.toModel(paciente);
    }

    @CheckSecurity.Pacientes.PodeCadastrarPaciente
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PacienteModel adicionar(@RequestBody @Valid PacienteInputComSenhaModel pacienteInputComSenhaModel) {
        List<GrupoModel> grupos = new ArrayList<>();
        // ID do Usuario Comum
        Grupo grupo = grupoService.buscarOuFalhar(3L);
        grupos.add(assemblerGrupo.toModel(grupo));
        pacienteInputComSenhaModel.setGrupos(grupos);
        pacienteInputComSenhaModel.setAtivado(true);

        Paciente paciente = disassembler.toDomainObjectComSenha(pacienteInputComSenhaModel);
//        eventPublisher.publishEvent(new RegistroCompletoEvent
//            (paciente));

        return assembler.toModel(pacienteService.salvar(paciente));
    }

    @CheckSecurity.Pacientes.PodeEditarPaciente
    @Override
    @PutMapping("/{pacienteId}")
    public PacienteModel atualizar(@PathVariable Long pacienteId,
        @RequestBody @Valid PacienteInputComSenhaModel pacienteInputComSenhaModel) {
        Paciente paciente = pacienteService.buscarOuFalhar(pacienteId);

        if (pacienteInputComSenhaModel.getSenha() != null || pacienteInputComSenhaModel.getSenha().isEmpty()) {
            String senhaCriptografada = new BCryptPasswordEncoder().encode(pacienteInputComSenhaModel.getSenha());
            pacienteInputComSenhaModel.setSenha(senhaCriptografada);
        }
        pacienteInputComSenhaModel.setAtivado(paciente.isAtivado());

        Paciente pacienteAlterado = disassembler.toDomainObjectComSenha(pacienteInputComSenhaModel);
        pacienteAlterado.setDataCadastro(paciente.getDataCadastro());
        pacienteAlterado = pacienteService.salvar(pacienteAlterado);
        return assembler.toModel(pacienteAlterado);
    }

    @CheckSecurity.Pacientes.PodeRemoverPaciente
    @Override
    @DeleteMapping("/{pacienteId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long pacienteId) {
        pacienteService.excluir(pacienteId);
    }

    @CheckSecurity.UsuariosGruposPermissoes.PodeAlterarPropriaSenha
    @Override
    @PutMapping("/{pacienteId}/senha")
    public void alterarSenha(@PathVariable Long pacienteId, @RequestBody @Valid SenhaInputModel senha) {
        pacienteService.alterarSenha(pacienteId, senha.getSenhaAtual(), senha.getNovaSenha());
    }

}
