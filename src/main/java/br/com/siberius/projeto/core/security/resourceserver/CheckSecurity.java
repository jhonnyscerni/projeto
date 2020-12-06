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

        @PreAuthorize("hasAuthority('SCOPE_WRITE') and (hasAuthority('EDITAR_USUARIOS') or "
            + "@projetoSecurity.usuarioAutenticadoIgual(#usuarioId))")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface PodeAlterarUsuario {

        }

        /**
         * USUARIO
         */

        @PreAuthorize("@projetoSecurity.podeEditarUsuarios()")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface PodeEditarUsuario {

        }

        @PreAuthorize("@projetoSecurity.podeRemoverUsuarios()")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface PodeRemoverUsuario {

        }


        @PreAuthorize("@projetoSecurity.podeConsultarUsuarios()")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface PodeConsultarUsuario {

        }

        @PreAuthorize("@projetoSecurity.podeCadastrarUsuarios()")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface PodeCadastrarUsuario {

        }

        /**
         * GRUPO
         */

        @PreAuthorize("@projetoSecurity.podeEditarGrupos()")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface PodeEditarGrupo {

        }

        @PreAuthorize("@projetoSecurity.podeRemoverGrupos()")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface PodeRemoverGrupo {

        }


        @PreAuthorize("@projetoSecurity.podeConsultarGrupos()")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface PodeConsultarGrupo {

        }

        @PreAuthorize("@projetoSecurity.podeCadastrarGrupos()")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface PodeCadastrarGrupo {

        }

        /**
         * PERMISSAO
         */

        @PreAuthorize("@projetoSecurity.podeEditarPermissoes()")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface PodeEditarPermissao {

        }

        @PreAuthorize("@projetoSecurity.podeRemoverPermissoes()")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface PodeRemoverPermissao {

        }


        @PreAuthorize("@projetoSecurity.podeConsultarPermissoes()")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface PodeConsultarPermissao {

        }

        @PreAuthorize("@projetoSecurity.podeCadastrarPermissoes()")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface PodeCadastrarPermissao {

        }

        /**
         * USUARIO GRUPO
         */

        @PreAuthorize("@projetoSecurity.podeConsultarUsuariosGrupos()")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface PodeConsultarUsuarioGrupo {

        }

        @PreAuthorize("@projetoSecurity.podeAssociarUsuariosGrupos()")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface PodeAssociarUsuarioGrupo {

        }

        @PreAuthorize("@projetoSecurity.podeDesassociarUsuariosGrupos()")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface PodeDesassociarUsuarioGrupo {

        }

        /**
         * GRUPO PERMISSAO
         */

        @PreAuthorize("@projetoSecurity.podeConsultarGruposPermissaos()")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface PodeConsultarGrupoPermissao {

        }

        @PreAuthorize("@projetoSecurity.podeAssociarGruposPermissaos()")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface PodeAssociarGrupoPermissao {

        }

        @PreAuthorize("@projetoSecurity.podeDesassociarGruposPermissaos()")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface PodeDesassociarGrupoPermissao {

        }

    }


}

