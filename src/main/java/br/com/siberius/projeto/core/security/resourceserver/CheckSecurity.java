package br.com.siberius.projeto.core.security.resourceserver;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import org.springframework.security.access.prepost.PreAuthorize;

public @interface CheckSecurity {

    public @interface UsuariosGruposPermissoes {

        @PreAuthorize("hasAuthority('SCOPE_WRITE') and "
            + "@projetoSecurity.usuarioAutenticadoIgual(#usuarioId)")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface PodeAlterarPropriaSenha {

        }

        @PreAuthorize("hasAuthority('SCOPE_WRITE') and (hasAuthority('EDITAR_USUARIOS_GRUPOS_PERMISSOES') or "
            + "@projetoSecurity.usuarioAutenticadoIgual(#usuarioId))")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface PodeAlterarUsuario {

        }

        @PreAuthorize("@projetoSecurity.podeEditarUsuariosGruposPermissoes()")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface PodeEditar {

        }

        @PreAuthorize("@projetoSecurity.podeRemoverUsuariosGruposPermissoes()")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface PodeRemover {

        }


        @PreAuthorize("@projetoSecurity.podeConsultarUsuariosGruposPermissoes()")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface PodeConsultar {

        }

    }


}

