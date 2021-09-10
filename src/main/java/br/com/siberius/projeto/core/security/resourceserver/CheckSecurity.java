package br.com.siberius.projeto.core.security.resourceserver;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;

public @interface CheckSecurity {

    /*
     *******************************************************************************************
     * Atendimentos
     * *******************************************************************************************
     */
    public @interface Atendimentos {

        /*
         * Editar
         */
        @PreAuthorize("@projetoSecurity.podeEditarAtendimentos()")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface PodeEditarAtendimento {
        }

        /*
         * Remover
         */
        @PreAuthorize("@projetoSecurity.podeRemoverAtendimentos()")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface PodeRemoverAtendimento {
        }

        /*
         * Consultar
         */
        @PreAuthorize("@projetoSecurity.podeConsultarAtendimentos()")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface PodeConsultarAtendimento {
        }

        /*
         * Cadastrar
         */
        @PreAuthorize("@projetoSecurity.podeCadastrarAtendimentos()")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface PodeCadastrarAtendimento {

        }
    }

    /*
     *******************************************************************************************
     * Cidades
     * *****************************************************************************************
     */
    public @interface Cidades {

        /*
         * Editar
         */
        @PreAuthorize("@projetoSecurity.podeEditarCidades()")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface PodeEditarCidade {
        }

        /*
         * Remover
         */
        @PreAuthorize("@projetoSecurity.podeRemoverCidades()")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface PodeRemoverCidade {
        }

        /*
         * Consultar
         */
        @PreAuthorize("@projetoSecurity.podeConsultarCidades()")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface PodeConsultarCidade {
        }

        /*
         * Cadastrar
         */
        @PreAuthorize("@projetoSecurity.podeCadastrarCidades()")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface PodeCadastrarCidade {

        }
    }

    /*
     *******************************************************************************************
     * Clinicas
     * *****************************************************************************************
     */
    public @interface Clinicas {

        /*
         * Editar
         */
        @PreAuthorize("@projetoSecurity.podeEditarCidades()")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface PodeEditarClinica {
        }

        /*
         * Remover
         */
        @PreAuthorize("@projetoSecurity.podeRemoverCidades()")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface PodeRemoverClinica {
        }

        /*
         * Consultar
         */
        @PreAuthorize("@projetoSecurity.podeConsultarCidades()")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface PodeConsultarClinica {
        }

        /*
         * Cadastrar
         */
        @PreAuthorize("@projetoSecurity.podeCadastrarCidades()")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface PodeCadastrarClinica {
        }
    }

    /*
     *******************************************************************************************
     * Consultas
     * *****************************************************************************************
     */
    public @interface Consultas {

        /*
         * Editar
         */
        @PreAuthorize("@projetoSecurity.podeEditarConsultas()")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface PodeEditarConsulta {
        }

        /*
         * Remover
         */
        @PreAuthorize("@projetoSecurity.podeRemoverConsultas()")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface PodeRemoverConsulta {
        }

        /*
         * Consultar
         */
        @PreAuthorize("@projetoSecurity.podeConsultarConsultas()")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface PodeConsultarConsulta {
        }

        /*
         * Cadastrar
         */
        @PreAuthorize("@projetoSecurity.podeCadastrarConsultas()")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface PodeCadastrarConsulta {
        }
    }

    /*
     *******************************************************************************************
     * Dashboard Clinicas
     * *****************************************************************************************
     */
    public @interface DashboardClinicas {

        /*
         * Consultar
         */
        @PreAuthorize("@projetoSecurity.podeConsultarDashboardClinicas()")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface PodeConsultarDashboardClinica {
        }

    }

    /*
     *******************************************************************************************
     * Dashboard Profissionais
     * *****************************************************************************************
     */
    public @interface DashboardProfissionais {

        /*
         * Consultar
         */
        @PreAuthorize("@projetoSecurity.podeConsultarDashboardProfissionais()")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface PodeConsultarDashboardProfissional {
        }

    }

    /*
     *******************************************************************************************
     * Estados
     * *****************************************************************************************
     */
    public @interface Estados {

        /*
         * Consultar
         */
        @PreAuthorize("@projetoSecurity.podeConsultarEstados()")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface PodeConsultarEstado {
        }

    }

    /*
     *******************************************************************************************
     * Pacientes
     * *****************************************************************************************
     */
    public @interface Pacientes {

        //        @PreAuthorize("@projetoSecurity.podeEditarPacientes() or "
//            + "@projetoSecurity.usuarioAutenticadoIgual(#profissionalId)")
        @PreAuthorize("@projetoSecurity.podeEditarPacientes()")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface PodeEditarPaciente {

        }

        //        @PreAuthorize("@projetoSecurity.podeRemoverPacientes() or "
//            + "@projetoSecurity.usuarioAutenticadoIgual(#profissionalId)")
        @PreAuthorize("@projetoSecurity.podeRemoverPacientes()")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface PodeRemoverPaciente {

        }


        //@PreAuthorize("@projetoSecurity.podeConsultarPacientes(#filter.profissionalId)" )
        @PreAuthorize("@projetoSecurity.podeConsultarPacientes()")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface PodeConsultarPaciente {

        }

        @PreAuthorize("@projetoSecurity.podeCadastrarPacientes()")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface PodeCadastrarPaciente {

        }

        //        @PreAuthorize("hasAuthority('SCOPE_READ') and isAuthenticated()")
//        @PostAuthorize("hasAuthority('SEG_BUSCAR_PACIENTES') or "
//            + "@projetoSecurity.usuarioAutenticadoIgual(returnObject.profissionalId)")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface PodeBuscar {
        }

    }

    /*
     *******************************************************************************************
     * Profissionais
     * *****************************************************************************************
     */
    public @interface Profissionais {

        //        @PreAuthorize("@projetoSecurity.podeEditarPacientes() or "
//            + "@projetoSecurity.usuarioAutenticadoIgual(#profissionalId)")
        @PreAuthorize("@projetoSecurity.podeEditarProfissionais()")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface PodeEditarProfissional {

        }

        //        @PreAuthorize("@projetoSecurity.podeRemoverPacientes() or "
//            + "@projetoSecurity.usuarioAutenticadoIgual(#profissionalId)")
        @PreAuthorize("@projetoSecurity.podeRemoverProfissionais()")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface PodeRemoverProfissional {

        }


        //@PreAuthorize("@projetoSecurity.podeConsultarPacientes(#filter.profissionalId)" )
        @PreAuthorize("@projetoSecurity.podeConsultarProfissionais()")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface PodeConsultarProfissional {

        }

        @PreAuthorize("@projetoSecurity.podeCadastrarProfissionais()")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface PodeCadastrarProfissional {

        }

        //        @PreAuthorize("hasAuthority('SCOPE_READ') and isAuthenticated()")
//        @PostAuthorize("hasAuthority('SEG_BUSCAR_PACIENTES') or "
//            + "@projetoSecurity.usuarioAutenticadoIgual(returnObject.profissionalId)")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface PodeBuscar {
        }

    }

    public @interface UsuariosGruposPermissoes {

        @PreAuthorize("hasAuthority('SCOPE_WRITE') and "
                + "@projetoSecurity.usuarioAutenticadoIgual(#usuarioId)")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface PodeAlterarPropriaSenha {

        }

        @PreAuthorize("hasAuthority('SCOPE_WRITE') and "
                + "@projetoSecurity.usuarioAutenticadoIgual(#usuarioId)")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface PodeConsultarUsuarioIgual {

        }

        @PreAuthorize("hasAuthority('SCOPE_WRITE') and (hasAuthority('SEG_EDITAR_USUARIOS') or "
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

