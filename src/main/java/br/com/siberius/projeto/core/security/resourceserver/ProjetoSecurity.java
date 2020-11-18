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

    public boolean podeConsultarUsuariosGruposPermissoes() {
        return temEscopoLeitura() && hasAuthority("CONSULTAR_USUARIOS_GRUPOS_PERMISSOES");
    }

    public boolean podeEditarUsuariosGruposPermissoes() {
        return temEscopoEscrita() && hasAuthority("EDITAR_USUARIOS_GRUPOS_PERMISSOES");
    }

    public boolean podeRemoverUsuariosGruposPermissoes() {
        return temEscopoEscrita() && hasAuthority("REMOVER_USUARIOS_GRUPOS_PERMISSOES");
    }

}
