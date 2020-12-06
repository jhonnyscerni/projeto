package br.com.siberius.projeto.core.security.resourceserver;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

@Component
public class ProjetoSecurity {


    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public boolean isAutenticado() {
        return getAuthentication().isAuthenticated();
    }

    public Long getUsuarioId() {
        Jwt jwt = (Jwt) getAuthentication().getPrincipal();

        return jwt.getClaim("usuario_id");
    }

    public boolean usuarioAutenticadoIgual(Long usuarioId) {
        return getUsuarioId() != null && usuarioId != null
            && getUsuarioId().equals(usuarioId);
    }

    public boolean hasAuthority(String authorityName) {
        return getAuthentication().getAuthorities().stream()
            .anyMatch(authority -> authority.getAuthority().equals(authorityName));
    }

    public boolean temEscopoEscrita() {
        return hasAuthority("SCOPE_WRITE");
    }

    public boolean temEscopoLeitura() {
        return hasAuthority("SCOPE_READ");
    }

    // Permissoes de Usuarios

    public boolean podeConsultarUsuarios() {
        return temEscopoLeitura() && hasAuthority("CONSULTAR_USUARIOS");
    }

    public boolean podeCadastrarUsuarios() {
        return temEscopoLeitura() && hasAuthority("CADASTRAR_USUARIOS");
    }

    public boolean podeEditarUsuarios() {
        return temEscopoEscrita() && hasAuthority("EDITAR_USUARIOS");
    }

    public boolean podeRemoverUsuarios() {
        return temEscopoEscrita() && hasAuthority("REMOVER_USUARIOS");
    }

    // Permissoes de Grupo

    public boolean podeConsultarGrupos() {
        return temEscopoLeitura() && hasAuthority("CONSULTAR_GRUPOS");
    }

    public boolean podeCadastrarGrupos() {
        return temEscopoLeitura() && hasAuthority("CADASTRAR_GRUPOS");
    }

    public boolean podeEditarGrupos() {
        return temEscopoEscrita() && hasAuthority("EDITAR_GRUPOS");
    }

    public boolean podeRemoverGrupos() {
        return temEscopoEscrita() && hasAuthority("REMOVER_GRUPOS");
    }

    // Permissoes de Permissao

    public boolean podeConsultarPermissoes() {
        return temEscopoLeitura() && hasAuthority("CONSULTAR_PERMISSOES");
    }

    public boolean podeCadastrarPermissoes() {
        return temEscopoLeitura() && hasAuthority("CADASTRAR_PERMISSOES");
    }

    public boolean podeEditarPermissoes() {
        return temEscopoEscrita() && hasAuthority("EDITAR_PERMISSOES");
    }

    public boolean podeRemoverPermissoes() {
        return temEscopoEscrita() && hasAuthority("REMOVER_PERMISSOES");
    }

    // Permissoes de Associacao de Usuario com Grupo

    public boolean podeConsultarUsuariosGrupos() {
        return temEscopoLeitura() && hasAuthority("CONSULTAR_USUARIOS_GRUPOS");
    }

    public boolean podeAssociarUsuariosGrupos() {
        return temEscopoLeitura() && hasAuthority("ASSOCIAR_USUARIOS_GRUPOS");
    }

    public boolean podeDesassociarUsuariosGrupos() {
        return temEscopoLeitura() && hasAuthority("DESASSOCIAR_USUARIOS_GRUPOS");
    }

    // Permissoes de Associacao de Grupo com Permissoes

    public boolean podeConsultarGruposPermissaos() {
        return temEscopoLeitura() && hasAuthority("CONSULTAR_GRUPOS_PERMISSOES");
    }

    public boolean podeAssociarGruposPermissaos() {
        return temEscopoLeitura() && hasAuthority("ASSOCIAR_GRUPOS_PERMISSOES");
    }

    public boolean podeDesassociarGruposPermissaos() {
        return temEscopoLeitura() && hasAuthority("DESASSOCIAR_GRUPOS_PERMISSOES");
    }
}
