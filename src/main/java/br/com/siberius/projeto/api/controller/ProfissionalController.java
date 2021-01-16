package br.com.siberius.projeto.api.controller;

import br.com.siberius.projeto.api.assembler.GrupoModelAssembler;
import br.com.siberius.projeto.api.assembler.ProfissionalModelAssembler;
import br.com.siberius.projeto.api.assembler.disassembler.ProfissionalInputModelDisassembler;
import br.com.siberius.projeto.api.event.RegistroCompletoEvent;
import br.com.siberius.projeto.api.model.GrupoModel;
import br.com.siberius.projeto.api.model.ProfissionalModel;
import br.com.siberius.projeto.api.model.input.ProfissionalInputComSenhaModel;
import br.com.siberius.projeto.api.model.input.SenhaInputModel;
import br.com.siberius.projeto.api.openapi.controller.ProfissionalControllerOpenApi;
import br.com.siberius.projeto.core.security.resourceserver.CheckSecurity;
import br.com.siberius.projeto.domain.model.Grupo;
import br.com.siberius.projeto.domain.model.Profissional;
import br.com.siberius.projeto.domain.model.Usuario;
import br.com.siberius.projeto.domain.repository.ProfissionalRepository;
import br.com.siberius.projeto.domain.repository.filter.ProfissionalFilter;
import br.com.siberius.projeto.domain.service.GrupoService;
import br.com.siberius.projeto.domain.service.ProfissionalService;
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
@RequestMapping(path = "/profissionais", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProfissionalController implements ProfissionalControllerOpenApi {

    @Autowired
    private ProfissionalRepository profissionalRepository;

    @Autowired
    private ProfissionalService profissionalService;

    @Autowired
    private GrupoService grupoService;

    @Autowired
    private ProfissionalModelAssembler assembler;

    @Autowired
    private GrupoModelAssembler assemblerGrupo;

    @Autowired
    private ProfissionalInputModelDisassembler disassembler;

    @Autowired
    ApplicationEventPublisher eventPublisher;

//    @CheckSecurity.UsuariosGruposPermissoes.PodeConsultarUsuario
    @Override
    @GetMapping
//    public List<UsuarioModel> listar() {
//        return assembler.toCollectionModel(usuarioRepository.findAll());
//    }
    public Page<ProfissionalModel> pesquisar(ProfissionalFilter filter, @PageableDefault(size = 10) Pageable pageable) {

        Page<Profissional> profissionalPage = profissionalRepository.findAll(
            ProfissionalSpecs.usandoFiltro(filter), pageable);

        List<ProfissionalModel> profissionalModelList = assembler
            .toCollectionModel(profissionalPage.getContent());

        return new PageImpl<>(
            profissionalModelList, pageable, profissionalPage.getTotalElements());
    }

//    @CheckSecurity.UsuariosGruposPermissoes.PodeConsultarUsuario
    @Override
    @GetMapping("/{profissionalId}")
    public ProfissionalModel buscar(@PathVariable Long profissionalId) {
        Profissional profissional = profissionalService.buscarOuFalhar(profissionalId);
        return assembler.toModel(profissional);
    }

    @PostMapping
    @Override
    @ResponseStatus(HttpStatus.CREATED)
    public ProfissionalModel adicionar(@RequestBody @Valid ProfissionalInputComSenhaModel profissionalInputModel) {
        List<GrupoModel> grupos = new ArrayList<>();
        // ID do Usuario Comum
        Grupo grupo = grupoService.buscarOuFalhar(3L);
        grupos.add(assemblerGrupo.toModel(grupo));
        profissionalInputModel.setGrupos(grupos);

        Profissional profissional = profissionalService.salvar(disassembler.toDomainObjectComSenha(profissionalInputModel));

        eventPublisher.publishEvent(new RegistroCompletoEvent
            (profissional));

        return assembler.toModel(profissional);
    }

    @PostMapping("/add-usuario-comum")
    @Override
    @ResponseStatus(HttpStatus.CREATED)
    public ProfissionalModel adicionarComum(@RequestBody @Valid ProfissionalInputComSenhaModel profissionalInputModel) {
        List<GrupoModel> grupos = new ArrayList<>();
        // ID do Usuario Comum
        Grupo grupo = grupoService.buscarOuFalhar(3L);
        grupos.add(assemblerGrupo.toModel(grupo));
        profissionalInputModel.setGrupos(grupos);

        Profissional profissional = profissionalService.salvar(disassembler.toDomainObjectComSenha(profissionalInputModel));

        eventPublisher.publishEvent(new RegistroCompletoEvent
            (profissional));

        return assembler.toModel(profissional);
    }

//    @CheckSecurity.UsuariosGruposPermissoes.PodeAlterarUsuario
    @Override
    @PutMapping("/{profissionalId}")
    public ProfissionalModel atualizar(@PathVariable Long profissionalId,
        @RequestBody @Valid ProfissionalInputComSenhaModel profissionalInputComSenhaModel) {
        Profissional profissional = profissionalService.buscarOuFalhar(profissionalId);

        if (profissionalInputComSenhaModel.getSenha() != null || profissionalInputComSenhaModel.getSenha().isEmpty()) {
            String senhaCriptografada = new BCryptPasswordEncoder().encode(profissionalInputComSenhaModel.getSenha());
            profissionalInputComSenhaModel.setSenha(senhaCriptografada);
        }
        profissionalInputComSenhaModel.setAtivado(profissional.isAtivado());

        Profissional profissionalAlterado = disassembler.toDomainObjectComSenha(profissionalInputComSenhaModel);
        profissionalAlterado.setDataCadastro(profissional.getDataCadastro());
        profissionalAlterado = profissionalService.salvar(profissionalAlterado);
        return assembler.toModel(profissionalAlterado);
    }

//    @CheckSecurity.UsuariosGruposPermissoes.PodeRemoverUsuario
    @Override
    @DeleteMapping("/{profissionalId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long profissionalId) {
        profissionalService.excluir(profissionalId);
    }

//    @CheckSecurity.UsuariosGruposPermissoes.PodeAlterarPropriaSenha
    @Override
    @PutMapping("/{profissionalId}/senha")
    public void alterarSenha(@PathVariable Long profissionalId, @RequestBody @Valid SenhaInputModel senha) {
        profissionalService.alterarSenha(profissionalId, senha.getSenhaAtual(), senha.getNovaSenha());
    }

}
