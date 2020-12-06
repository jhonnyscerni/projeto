package br.com.siberius.projeto.api.controller;

import br.com.siberius.projeto.api.assembler.PermissaoModelAssembler;
import br.com.siberius.projeto.api.assembler.disassembler.PermissaoModelDisassembler;
import br.com.siberius.projeto.api.model.PermissaoModel;
import br.com.siberius.projeto.api.model.input.PermissaoInputModel;
import br.com.siberius.projeto.api.openapi.controller.PermissaoControllerOpenApi;
import br.com.siberius.projeto.core.security.resourceserver.CheckSecurity;
import br.com.siberius.projeto.domain.model.Permissao;
import br.com.siberius.projeto.domain.repository.PermissaoRepository;
import br.com.siberius.projeto.domain.service.PermissaoService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/permissoes", produces = MediaType.APPLICATION_JSON_VALUE)
public class PermissaoController implements PermissaoControllerOpenApi {

    @Autowired
    private PermissaoRepository permissaoRepository;

    @Autowired
    private PermissaoModelAssembler assembler;

    @Autowired
    private PermissaoModelDisassembler disassembler;

    @Autowired
    private PermissaoService permissaoService;

    @CheckSecurity.UsuariosGruposPermissoes.PodeConsultarPermissao
    @Override
    @GetMapping
    public List<PermissaoModel> listar() {
        return assembler.toCollectionModel(permissaoRepository.findAll());
    }

    @CheckSecurity.UsuariosGruposPermissoes.PodeConsultarPermissao
    @Override
    @GetMapping("/{permissaoId}")
    public PermissaoModel buscar(@PathVariable Long permissaoId) {
        return assembler.toModel(permissaoService.buscarOuFalhar(permissaoId));
    }

    @CheckSecurity.UsuariosGruposPermissoes.PodeCadastrarPermissao
    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PermissaoModel adicionar(@RequestBody @Valid PermissaoInputModel permissaoInputModel) {
        return assembler.toModel(permissaoService.salvar(disassembler.toDomainObject(permissaoInputModel)));
    }

    @CheckSecurity.UsuariosGruposPermissoes.PodeEditarPermissao
    @Override
    @PutMapping("/{permissaoId}")
    public PermissaoModel atualizar(@PathVariable Long permissaoId, @RequestBody @Valid PermissaoInputModel permissaoInputModel) {
        Permissao permissao = permissaoService.buscarOuFalhar(permissaoId);
        disassembler.copyToDomainObject(permissaoInputModel, permissao);
        return assembler.toModel(permissaoService.salvar(permissao));
    }
}
