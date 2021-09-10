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

    // Atendimentos

    public boolean podeConsultarAtendimentos() {
        return temEscopoLeitura() && (hasAuthority("SEG_CONSULTAR_ATENDIMENTOS"));
    }

    public boolean podeCadastrarAtendimentos() {
        return temEscopoLeitura() && isAutenticado() && hasAuthority("SEG_CADASTRAR_ATENDIMENTOS");
    }

    public boolean podeEditarAtendimentos() {
        return temEscopoEscrita() && isAutenticado() && hasAuthority("SEG_EDITAR_ATENDIMENTOS");
    }

    public boolean podeRemoverAtendimentos() {
        return temEscopoEscrita() && isAutenticado() && hasAuthority("SEG_REMOVER_ATENDIMENTOS");
    }

    // Cidades

    public boolean podeConsultarCidades() {
        return temEscopoLeitura() && (hasAuthority("SEG_CONSULTAR_CIDADES"));
    }

    public boolean podeCadastrarCidades() {
        return temEscopoLeitura() && isAutenticado() && hasAuthority("SEG_CADASTRAR_CIDADES");
    }

    public boolean podeEditarCidades() {
        return temEscopoEscrita() && isAutenticado() && hasAuthority("SEG_EDITAR_CIDADES");
    }

    public boolean podeRemoverCidades() {
        return temEscopoEscrita() && isAutenticado() && hasAuthority("SEG_REMOVER_CIDADES");
    }

    // Clinicas

    public boolean podeConsultarClinicas() {
        return temEscopoLeitura() && (hasAuthority("SEG_CONSULTAR_CLINICAS"));
    }

    public boolean podeCadastrarClinicas() {
        return temEscopoLeitura() && isAutenticado() && hasAuthority("SEG_CADASTRAR_CLINICAS");
    }

    public boolean podeEditarClinicas() {
        return temEscopoEscrita() && isAutenticado() && hasAuthority("SEG_EDITAR_CLINICAS");
    }

    public boolean podeRemoverClinicas() {
        return temEscopoEscrita() && isAutenticado() && hasAuthority("SEG_REMOVER_CLINICAS");
    }

    // Consultas

    public boolean podeConsultarConsultas() {
        return temEscopoLeitura() && (hasAuthority("SEG_CONSULTAR_CONSULTAS"));
    }

    public boolean podeCadastrarConsultas() {
        return temEscopoLeitura() && isAutenticado() && hasAuthority("SEG_CADASTRAR_CONSULTAS");
    }

    public boolean podeEditarConsultas() {
        return temEscopoEscrita() && isAutenticado() && hasAuthority("SEG_EDITAR_CONSULTAS");
    }

    public boolean podeRemoverConsultas() {
        return temEscopoEscrita() && isAutenticado() && hasAuthority("SEG_REMOVER_CONSULTAS");
    }

    // Dashboard Clinicas

    public boolean podeConsultarDashboardClinicas() {
        return temEscopoLeitura() && (hasAuthority("SEG_CONSULTAR_DASHBOARD_CLINICAS"));
    }

    // Dashboard Profissionais

    public boolean podeConsultarDashboardProfissionais() {
        return temEscopoLeitura() && (hasAuthority("SEG_CONSULTAR_DASHBOARD_PROFISSIONAIS"));
    }

    // Estados

    public boolean podeConsultarEstados() {
        return temEscopoLeitura() && (hasAuthority("SEG_CONSULTAR_ESTADOS"));
    }

    // Pacientes

    public boolean podeConsultarPacientes() {
        return temEscopoLeitura() && (hasAuthority("SEG_CONSULTAR_PACIENTES"));
    }

    public boolean podeCadastrarPacientes() {
        return temEscopoLeitura() && isAutenticado() && hasAuthority("SEG_CADASTRAR_PACIENTES");
    }

    public boolean podeEditarPacientes() {
        return temEscopoEscrita() && isAutenticado() && hasAuthority("SEG_EDITAR_PACIENTES");
    }

    public boolean podeRemoverPacientes() {
        return temEscopoEscrita() && isAutenticado() && hasAuthority("SEG_REMOVER_PACIENTES");
    }

    // Profissionais

    public boolean podeConsultarProfissionais() {
        return temEscopoLeitura() && (hasAuthority("SEG_CONSULTAR_PROFISSIONAIS"));
    }

    public boolean podeCadastrarProfissionais() {
        return temEscopoLeitura() && isAutenticado() && hasAuthority("SEG_CADASTRAR_PROFISSIONAIS");
    }

    public boolean podeEditarProfissionais() {
        return temEscopoEscrita() && isAutenticado() && hasAuthority("SEG_EDITAR_PROFISSIONAIS");
    }

    public boolean podeRemoverProfissionais() {
        return temEscopoEscrita() && isAutenticado() && hasAuthority("SEG_REMOVER_PROFISSIONAIS");
    }

    // Lancamentos

    public boolean podeConsultarLancamentos() {
        return temEscopoLeitura() && (hasAuthority("SEG_CONSULTAR_LANCAMENTOS"));
    }

    public boolean podeCadastrarLancamentos() {
        return temEscopoLeitura() && isAutenticado() && hasAuthority("SEG_CADASTRAR_LANCAMENTOS");
    }

    public boolean podeEditarLancamentos() {
        return temEscopoEscrita() && isAutenticado() && hasAuthority("SEG_EDITAR_LANCAMENTOS");
    }

    public boolean podeRemoverLancamentos() {
        return temEscopoEscrita() && isAutenticado() && hasAuthority("SEG_REMOVER_LANCAMENTOS");
    }

    // Formas de Pagamento

    public boolean podeConsultarFormasPagamento() {
        return temEscopoLeitura() && (hasAuthority("SEG_CONSULTAR_FORMAS_PAGAMENTO"));
    }

    public boolean podeCadastrarFormasPagamento() {
        return temEscopoLeitura() && isAutenticado() && hasAuthority("SEG_CADASTRAR_FORMAS_PAGAMENTO");
    }

    public boolean podeEditarFormasPagamento() {
        return temEscopoEscrita() && isAutenticado() && hasAuthority("SEG_EDITAR_FORMAS_PAGAMENTO");
    }

    public boolean podeRemoverFormasPagamento() {
        return temEscopoEscrita() && isAutenticado() && hasAuthority("SEG_REMOVER_FORMAS_PAGAMENTO");
    }

}
