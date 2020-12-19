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
        return temEscopoLeitura() && hasAuthority("SEG_CONSULTAR_USUARIOS");
    }

    public boolean podeCadastrarUsuarios() {
        return temEscopoLeitura() && hasAuthority("SEG_CADASTRAR_USUARIOS");
    }

    public boolean podeEditarUsuarios() {
        return temEscopoEscrita() && hasAuthority("SEG_EDITAR_USUARIOS");
    }

    public boolean podeRemoverUsuarios() {
        return temEscopoEscrita() && hasAuthority("SEG_REMOVER_USUARIOS");
    }

    // Permissoes de Grupo

    public boolean podeConsultarGrupos() {
        return temEscopoLeitura() && hasAuthority("SEG_CONSULTAR_GRUPOS");
    }

    public boolean podeCadastrarGrupos() {
        return temEscopoLeitura() && hasAuthority("SEG_CADASTRAR_GRUPOS");
    }

    public boolean podeEditarGrupos() {
        return temEscopoEscrita() && hasAuthority("SEG_EDITAR_GRUPOS");
    }

    public boolean podeRemoverGrupos() {
        return temEscopoEscrita() && hasAuthority("SEG_REMOVER_GRUPOS");
    }

    // Permissoes de Permissao

    public boolean podeConsultarPermissoes() {
        return temEscopoLeitura() && hasAuthority("SEG_CONSULTAR_PERMISSOES");
    }

    public boolean podeCadastrarPermissoes() {
        return temEscopoLeitura() && hasAuthority("SEG_CADASTRAR_PERMISSOES");
    }

    public boolean podeEditarPermissoes() {
        return temEscopoEscrita() && hasAuthority("SEG_EDITAR_PERMISSOES");
    }

    public boolean podeRemoverPermissoes() {
        return temEscopoEscrita() && hasAuthority("SEG_REMOVER_PERMISSOES");
    }

    // Permissoes de Associacao de Usuario com Grupo

    public boolean podeConsultarUsuariosGrupos() {
        return temEscopoLeitura() && hasAuthority("SEG_CONSULTAR_USUARIOS_GRUPOS");
    }

    public boolean podeAssociarUsuariosGrupos() {
        return temEscopoLeitura() && hasAuthority("SEG_ASSOCIAR_USUARIOS_GRUPOS");
    }

    public boolean podeDesassociarUsuariosGrupos() {
        return temEscopoLeitura() && hasAuthority("SEG_DESASSOCIAR_USUARIOS_GRUPOS");
    }

    // Permissoes de Associacao de Grupo com Permissoes

    public boolean podeConsultarGruposPermissaos() {
        return temEscopoLeitura() && hasAuthority("SEG_CONSULTAR_GRUPOS_PERMISSOES");
    }

    public boolean podeAssociarGruposPermissaos() {
        return temEscopoLeitura() && hasAuthority("SEG_ASSOCIAR_GRUPOS_PERMISSOES");
    }

    public boolean podeDesassociarGruposPermissaos() {
        return temEscopoLeitura() && hasAuthority("SEG_DESASSOCIAR_GRUPOS_PERMISSOES");
    }
}
