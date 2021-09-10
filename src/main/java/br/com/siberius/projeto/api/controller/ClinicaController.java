package br.com.siberius.projeto.api.controller;

import br.com.siberius.projeto.api.assembler.ClinicaModelAssembler;
import br.com.siberius.projeto.api.assembler.GrupoModelAssembler;
import br.com.siberius.projeto.api.assembler.disassembler.ClinicaInputModelDisassembler;
import br.com.siberius.projeto.api.event.RegistroCompletoEvent;
import br.com.siberius.projeto.api.model.ClinicaModel;
import br.com.siberius.projeto.api.model.GrupoModel;
import br.com.siberius.projeto.api.model.ProfissionalModel;
import br.com.siberius.projeto.api.model.input.ClinicaInputComSenhaModel;
import br.com.siberius.projeto.api.model.input.ProfissionalInputComSenhaModel;
import br.com.siberius.projeto.api.model.input.SenhaInputModel;
import br.com.siberius.projeto.api.openapi.controller.ClinicaControllerOpenApi;
import br.com.siberius.projeto.core.security.resourceserver.CheckSecurity;
import br.com.siberius.projeto.domain.model.Clinica;
import br.com.siberius.projeto.domain.model.Grupo;
import br.com.siberius.projeto.domain.model.Profissional;
import br.com.siberius.projeto.domain.repository.ClinicaRepository;
import br.com.siberius.projeto.domain.repository.filter.ClinicaFilter;
import br.com.siberius.projeto.domain.repository.filter.ProfissionalFilter;
import br.com.siberius.projeto.domain.service.ClinicaService;
import br.com.siberius.projeto.domain.service.GrupoService;
import br.com.siberius.projeto.infrastructure.repository.ClinicaSpecs;
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
@RequestMapping(path = "/clinicas", produces = MediaType.APPLICATION_JSON_VALUE)
public class ClinicaController implements ClinicaControllerOpenApi {

    @Autowired
    private ClinicaRepository clinicaRepository;

    @Autowired
    private ClinicaService clinicaService;

    @Autowired
    private GrupoService grupoService;

    @Autowired
    private ClinicaModelAssembler assembler;

    @Autowired
    private GrupoModelAssembler assemblerGrupo;

    @Autowired
    private ClinicaInputModelDisassembler disassembler;

    @Autowired
    ApplicationEventPublisher eventPublisher;


    @CheckSecurity.Clinicas.PodeConsultarClinica
    @Override
    @GetMapping
//    public List<UsuarioModel> listar() {
//        return assembler.toCollectionModel(usuarioRepository.findAll());
//    }
    public Page<ClinicaModel> pesquisar(ClinicaFilter filter, @PageableDefault(size = 10) Pageable pageable) {

        Page<Clinica> clinicaPage = clinicaRepository.findAll(
            ClinicaSpecs.usandoFiltro(filter), pageable);

        List<ClinicaModel> clinicaModelList = assembler
            .toCollectionModel(clinicaPage.getContent());

        return new PageImpl<>(
            clinicaModelList, pageable, clinicaPage.getTotalElements());
    }

    @CheckSecurity.Clinicas.PodeConsultarClinica
    @Override
    @GetMapping("/lista")
    public List<ClinicaModel> pesquisar(ClinicaFilter filter) {

        List<Clinica> clinicaList = clinicaRepository.findAll(
                ClinicaSpecs.usandoFiltro(filter));
        return assembler.toCollectionModel(clinicaList);
    }

//    @CheckSecurity.UsuariosGruposPermissoes.PodeConsultarUsuario
    @Override
    @GetMapping("/{clinicaId}")
    public ClinicaModel buscar(@PathVariable Long clinicaId) {
        Clinica clinica = clinicaService.buscarOuFalhar(clinicaId);
        return assembler.toModel(clinica);
    }

    @CheckSecurity.Clinicas.PodeConsultarClinica
    @PostMapping
    @Override
    @ResponseStatus(HttpStatus.CREATED)
    public ClinicaModel adicionar(@RequestBody @Valid ClinicaInputComSenhaModel clinicaInputComSenhaModel) {
        List<GrupoModel> grupos = new ArrayList<>();
        // ID do Usuario Comum
        Grupo grupo = grupoService.buscarOuFalhar(2L);
        grupos.add(assemblerGrupo.toModel(grupo));
        clinicaInputComSenhaModel.setGrupos(grupos);

        Clinica cLinica = clinicaService.salvar(disassembler.toDomainObjectComSenha(clinicaInputComSenhaModel));

        eventPublisher.publishEvent(new RegistroCompletoEvent
            (cLinica));

        return assembler.toModel(cLinica);
    }

    @PostMapping("/add-usuario-comum")
    @Override
    @ResponseStatus(HttpStatus.CREATED)
    public ClinicaModel adicionarComum(@RequestBody @Valid ClinicaInputComSenhaModel clinicaInputComSenhaModel) {
        List<GrupoModel> grupos = new ArrayList<>();
        // ID do Usuario Comum
        Grupo grupo = grupoService.buscarOuFalhar(2L);
        grupos.add(assemblerGrupo.toModel(grupo));
        clinicaInputComSenhaModel.setGrupos(grupos);

        Clinica cLinica = clinicaService.salvar(disassembler.toDomainObjectComSenha(clinicaInputComSenhaModel));

        eventPublisher.publishEvent(new RegistroCompletoEvent
            (cLinica));

        return assembler.toModel(cLinica);
    }

    @CheckSecurity.Clinicas.PodeEditarClinica
    @Override
    @PutMapping("/{clinicaId}")
    public ClinicaModel atualizar(@PathVariable Long clinicaId,
        @RequestBody @Valid ClinicaInputComSenhaModel clinicaInputComSenhaModel) {
        Clinica clinica = clinicaService.buscarOuFalhar(clinicaId);

        if (clinicaInputComSenhaModel.getSenha() != null || clinicaInputComSenhaModel.getSenha().isEmpty()) {
            String senhaCriptografada = new BCryptPasswordEncoder().encode(clinicaInputComSenhaModel.getSenha());
            clinicaInputComSenhaModel.setSenha(senhaCriptografada);
        }
        clinicaInputComSenhaModel.setAtivado(clinica.isAtivado());

        Clinica clinicaAlterado = disassembler.toDomainObjectComSenha(clinicaInputComSenhaModel);
        clinicaAlterado.setDataCadastro(clinica.getDataCadastro());
        clinicaAlterado = clinicaService.salvar(clinicaAlterado);
        return assembler.toModel(clinicaAlterado);
    }

    @CheckSecurity.Clinicas.PodeRemoverClinica
    @Override
    @DeleteMapping("/{clinicaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long clinicaId) {
        clinicaService.excluir(clinicaId);
    }

    //@CheckSecurity.UsuariosGruposPermissoes.PodeAlterarPropriaSenha
    @Override
    @PutMapping("/{profissionalId}/senha")
    public void alterarSenha(@PathVariable Long clinicaId, @RequestBody @Valid SenhaInputModel senha) {
        clinicaService.alterarSenha(clinicaId, senha.getSenhaAtual(), senha.getNovaSenha());
    }
}
