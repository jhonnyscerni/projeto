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
import br.com.siberius.projeto.domain.model.FotoPerfil;
import br.com.siberius.projeto.domain.model.Grupo;
import br.com.siberius.projeto.domain.model.Profissional;
import br.com.siberius.projeto.domain.repository.ProfissionalRepository;
import br.com.siberius.projeto.domain.repository.filter.ProfissionalFilter;
import br.com.siberius.projeto.domain.service.GrupoService;
import br.com.siberius.projeto.domain.service.ProfissionalService;
import br.com.siberius.projeto.infrastructure.repository.ProfissionalSpecs;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

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

    @CheckSecurity.Profissionais.PodeConsultarProfissional
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

    @CheckSecurity.Profissionais.PodeConsultarProfissional
    @Override
    @GetMapping("/lista")
    public List<ProfissionalModel> pesquisar(ProfissionalFilter filter) {

        List<Profissional> profissionalList = profissionalRepository.findAll(
            ProfissionalSpecs.usandoFiltro(filter));
        return assembler.toCollectionModel(profissionalList);
    }

    //    @CheckSecurity.UsuariosGruposPermissoes.PodeConsultarUsuario
    @Override
    @GetMapping("/{profissionalId}")
    public ProfissionalModel buscar(@PathVariable Long profissionalId) {
        Profissional profissional = profissionalService.buscarOuFalhar(profissionalId);
        return assembler.toModel(profissional);
    }

    @CheckSecurity.Profissionais.PodeCadastrarProfissional
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Override
    @ResponseStatus(HttpStatus.CREATED)
    public ProfissionalModel adicionar(@RequestPart(value = "arquivo", required = false) MultipartFile arquivo,
        @RequestPart("profissional") ProfissionalInputComSenhaModel profissionalInputModel) throws IOException {
        List<GrupoModel> grupos = new ArrayList<>();
        // ID do Usuario Comum
        Grupo grupo = grupoService.buscarOuFalhar(3L);
        grupos.add(assemblerGrupo.toModel(grupo));
        profissionalInputModel.setGrupos(grupos);

        // Foto
        Profissional profissional;
        if (arquivo != null) {
            FotoPerfil foto = getFotoPerfil(arquivo);
            profissional = profissionalService.salvar(
                disassembler.toDomainObjectComSenha(profissionalInputModel), foto, arquivo.getInputStream());

        } else {
            profissional = profissionalService.salvarComum(
                disassembler.toDomainObjectComSenha(profissionalInputModel));

        }

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

        Profissional profissional = profissionalService.salvarComum(disassembler.toDomainObjectComSenha(profissionalInputModel));

        eventPublisher.publishEvent(new RegistroCompletoEvent
            (profissional));

        return assembler.toModel(profissional);
    }

    @CheckSecurity.Profissionais.PodeEditarProfissional
    @Override
    @PutMapping(value = "/{profissionalId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ProfissionalModel atualizar(@PathVariable Long profissionalId,
        @RequestPart(value = "arquivo", required = false) MultipartFile arquivo,
        @RequestPart("profissional") ProfissionalInputComSenhaModel profissionalInputComSenhaModel) throws IOException {
        Profissional profissional = profissionalService.buscarOuFalhar(profissionalId);

        profissionalInputComSenhaModel.setAtivado(profissional.isAtivado());
        Profissional profissionalAlterado = disassembler.toDomainObjectComSenha(profissionalInputComSenhaModel);
        profissionalAlterado.setDataCadastro(profissional.getDataCadastro());

        List<Grupo> grupos = new ArrayList<>();
        // ID do Usuario Comum
        Grupo grupo = grupoService.buscarOuFalhar(3L);
        grupos.add(grupo);
        profissionalAlterado.setGrupos(grupos);
        // Foto
        if (arquivo == null && (profissional.getFotoPerfil() == null || profissional.getFotoPerfil() != null)) {
            profissionalAlterado.setFotoPerfil(profissional.getFotoPerfil());
            profissionalAlterado = profissionalService.salvarComum(profissionalAlterado);

        }
        else {

            FotoPerfil foto = getFotoPerfil(arquivo);
            profissionalAlterado.setFotoPerfil(foto);
            profissionalAlterado = profissionalService.salvar(profissionalAlterado, foto, arquivo.getInputStream());
        }

        return assembler.toModel(profissionalAlterado);
    }

    @CheckSecurity.Profissionais.PodeRemoverProfissional
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

    private FotoPerfil getFotoPerfil(
        @RequestPart(value = "arquivo", required = false) MultipartFile arquivo) {
        FotoPerfil foto = new FotoPerfil();
        foto.setContentType(arquivo.getContentType());
        foto.setTamanho(arquivo.getSize());
        foto.setNomeArquivo(arquivo.getOriginalFilename());
        return foto;
    }
}
