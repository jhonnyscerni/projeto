package br.com.siberius.projeto.infrastructure.repository;

import br.com.siberius.projeto.domain.model.Atendimento;
import br.com.siberius.projeto.domain.model.Profissional;
import br.com.siberius.projeto.domain.repository.filter.AtendimentoFilter;
import br.com.siberius.projeto.domain.service.ProfissionalService;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.From;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;

public class AtendimentoSpecs {



    public static Specification<Atendimento> usandoFiltro(AtendimentoFilter filtro) {
        return (root, query, builder) -> {

            From<?, ?> consulta = root.join("consulta", JoinType.LEFT);

            root.fetch("consulta").fetch("clinica").fetch("grupos");
            root.fetch("consulta").fetch("clinica").fetch("grupos").fetch("permissoes");
            root.fetch("consulta").fetch("clinica").fetch("cidade");
            root.fetch("consulta").fetch("clinica").fetch("cidade").fetch("estado");

            root.fetch("consulta").fetch("paciente").fetch("grupos");
            root.fetch("consulta").fetch("paciente").fetch("cidade");

            root.fetch("consulta").fetch("profissional").fetch("grupos");
            root.fetch("consulta").fetch("profissional").fetch("cidade");


            List<Predicate> predicates = new ArrayList<Predicate>();

            if (filtro.getPacienteId()!= null) {
                predicates.add(builder.equal(consulta.get("paciente"), filtro.getPacienteId()));
            }
            if (filtro.getProfissionalId() != null) {
                predicates.add(builder.equal(consulta.get("profissional"), filtro.getProfissionalId()));
            }

            if (filtro.getClinicaId() != null) {
                predicates.add(builder.equal(consulta.get("clinica"), filtro.getClinicaId()));
            }

            if (filtro.getNomePaciente() != null) {
                predicates.add(builder.like(consulta.join("paciente").get("nome"), "%" + filtro.getNomePaciente() + "%"));
            }

            if (filtro.getEmailPaciente() != null) {
                predicates.add(builder.like(consulta.join("paciente").get("email"), "%" + filtro.getEmailPaciente() + "%"));
            }

            if (filtro.getNomeProfissional() != null) {
                predicates.add(builder.like(consulta.join("profissional").get("nome"), "%" + filtro.getNomeProfissional() + "%"));
            }

            if (filtro.getEmailProfissional() != null) {
                predicates.add(builder.like(consulta.join("profissional").get("email"), "%" + filtro.getEmailProfissional() + "%"));
            }

            if (filtro.getId() != null) {
                predicates.add(builder.equal(root.get("id"), filtro.getId()));
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
