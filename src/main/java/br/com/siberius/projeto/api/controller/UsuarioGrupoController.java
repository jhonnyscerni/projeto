package br.com.siberius.projeto.api.controller;

import br.com.siberius.projeto.api.assembler.GrupoModelAssembler;
import br.com.siberius.projeto.api.model.GrupoModel;
import br.com.siberius.projeto.api.openapi.controller.UsuarioGrupoControllerOpenApi;
import br.com.siberius.projeto.core.security.resourceserver.CheckSecurity;
import br.com.siberius.projeto.domain.model.Usuario;
import br.com.siberius.projeto.domain.service.UsuarioService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/usuarios/{usuarioId}/grupos")
public class UsuarioGrupoController implements UsuarioGrupoControllerOpenApi {


    private UsuarioService usuarioService;

    @Autowired
    private GrupoModelAssembler assembler;

    @CheckSecurity.UsuariosGruposPermissoes.PodeConsultarUsuarioGrupo
    @Override
    @GetMapping
    public List<GrupoModel> listar(@PathVariable Long usuarioId) {
        Usuario usuario = usuarioService.buscarOuFalhar(usuarioId);
        return assembler.toCollectionModel(usuario.getGrupos());
    }

    @CheckSecurity.UsuariosGruposPermissoes.PodeAssociarUsuarioGrupo
    @Override
    @PutMapping("/{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associar(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
        usuarioService.associarGrupo(usuarioId, grupoId);
    }

    @CheckSecurity.UsuariosGruposPermissoes.PodeDesassociarUsuarioGrupo
    @Override
    @DeleteMapping("/{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void desassociar(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
        usuarioService.desassociarGrupo(usuarioId, grupoId);
    }

}
