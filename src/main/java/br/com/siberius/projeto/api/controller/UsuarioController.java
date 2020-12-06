package br.com.siberius.projeto.api.controller;

import br.com.siberius.projeto.api.assembler.UsuarioModelAssembler;
import br.com.siberius.projeto.api.assembler.disassembler.UsuarioInputModelDisassembler;
import br.com.siberius.projeto.api.model.UsuarioModel;
import br.com.siberius.projeto.api.model.input.SenhaInputModel;
import br.com.siberius.projeto.api.model.input.UsuarioInputComSenhaModel;
import br.com.siberius.projeto.api.model.input.UsuarioInputModel;
import br.com.siberius.projeto.api.openapi.controller.UsuarioControllerOpenApi;
import br.com.siberius.projeto.core.security.resourceserver.CheckSecurity;
import br.com.siberius.projeto.domain.model.Usuario;
import br.com.siberius.projeto.domain.repository.UsuarioRepository;
import br.com.siberius.projeto.domain.service.UsuarioService;
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
@RequestMapping(path = "/usuarios", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsuarioController implements UsuarioControllerOpenApi {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioModelAssembler assembler;

    @Autowired
    private UsuarioInputModelDisassembler disassembler;

    @CheckSecurity.UsuariosGruposPermissoes.PodeConsultarUsuario
    @Override
    @GetMapping
    public List<UsuarioModel> listar() {
        return assembler.toCollectionModel(usuarioRepository.findAll());
    }

    @CheckSecurity.UsuariosGruposPermissoes.PodeConsultarUsuario
    @Override
    @GetMapping("/{usuarioId}")
    public UsuarioModel buscar(@PathVariable Long usuarioId) {
        Usuario usuario = usuarioService.buscarOuFalhar(usuarioId);
        return assembler.toModel(usuario);
    }

    @CheckSecurity.UsuariosGruposPermissoes.PodeCadastrarUsuario
    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioModel adicionar(@RequestBody @Valid UsuarioInputComSenhaModel usuarioInput) {
        Usuario usuario = usuarioService.salvar(disassembler.toDomainObject(usuarioInput));
        return assembler.toModel(usuario);
    }

    @CheckSecurity.UsuariosGruposPermissoes.PodeAlterarUsuario
    @Override
    @PutMapping("/{usuarioId}")
    public UsuarioModel atualizar(@PathVariable Long usuarioId,
        @RequestBody @Valid UsuarioInputComSenhaModel usuarioInput) {
        Usuario usuario = usuarioService.buscarOuFalhar(usuarioId);
        disassembler.copyToDomainObject(usuarioInput, usuario);
        usuario = usuarioService.salvar(usuario);
        return assembler.toModel(usuario);
    }

    @CheckSecurity.UsuariosGruposPermissoes.PodeRemoverUsuario
    @Override
    @DeleteMapping("/{usuarioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long usuarioId) {
        usuarioService.excluir(usuarioId);
    }

    @CheckSecurity.UsuariosGruposPermissoes.PodeAlterarPropriaSenha
    @Override
    @PutMapping("/{usuarioId}/senha")
    public void alterarSenha(@PathVariable Long usuarioId, @RequestBody @Valid SenhaInputModel senha) {
        usuarioService.alterarSenha(usuarioId, senha.getSenhaAtual(), senha.getNovaSenha());
    }
}
