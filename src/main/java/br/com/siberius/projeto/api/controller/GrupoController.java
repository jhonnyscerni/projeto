package br.com.siberius.projeto.api.controller;

import br.com.siberius.projeto.api.assembler.GrupoModelAssembler;
import br.com.siberius.projeto.api.assembler.disassembler.GrupoInputModelDisassembler;
import br.com.siberius.projeto.api.model.GrupoModel;
import br.com.siberius.projeto.api.model.input.GrupoInputModel;
import br.com.siberius.projeto.api.openapi.controller.GrupoControllerOpenApi;
import br.com.siberius.projeto.core.security.resourceserver.CheckSecurity;
import br.com.siberius.projeto.domain.model.Grupo;
import br.com.siberius.projeto.domain.repository.GrupoRepository;
import br.com.siberius.projeto.domain.service.GrupoService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping(path = "/grupos", produces = MediaType.APPLICATION_JSON_VALUE)
public class GrupoController implements GrupoControllerOpenApi {

    @Autowired
    private GrupoRepository grupoRepository;

    @Autowired
    private GrupoService grupoService;

    @Autowired
    private GrupoModelAssembler assembler;

    @Autowired
    private GrupoInputModelDisassembler disassembler;


    @CheckSecurity.UsuariosGruposPermissoes.PodeConsultarGrupo
    @Override
    @GetMapping
    public List<GrupoModel> listar() {
        return assembler.toCollectionModel(grupoRepository.findAll());
    }

    @CheckSecurity.UsuariosGruposPermissoes.PodeConsultarGrupo
    @Override
    @GetMapping("/{grupoId}")
    public GrupoModel buscar(@PathVariable Long grupoId) {
        return assembler.toModel(grupoService.buscarOuFalhar(grupoId));
    }

    @CheckSecurity.UsuariosGruposPermissoes.PodeCadastrarGrupo
    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GrupoModel adicionar(@RequestBody @Valid GrupoInputModel grupoInput) {
        Grupo grupo = disassembler.toDomainObject(grupoInput);
        return assembler.toModel(grupoService.salvar(grupo));
    }

    @CheckSecurity.UsuariosGruposPermissoes.PodeEditarGrupo
    @Override
    @PutMapping("/{grupoId}")
    public GrupoModel atualizar(@PathVariable Long grupoId, @RequestBody @Valid GrupoInputModel grupoInput) {
        Grupo grupo = grupoService.buscarOuFalhar(grupoId);
        disassembler.copyToDomainObject(grupoInput, grupo);
        grupo = grupoService.salvar(grupo);
        return assembler.toModel(grupo);
    }

    @CheckSecurity.UsuariosGruposPermissoes.PodeRemoverGrupo
    @Override
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(Long grupoId) {
        grupoService.excluir(grupoId);
    }
}
