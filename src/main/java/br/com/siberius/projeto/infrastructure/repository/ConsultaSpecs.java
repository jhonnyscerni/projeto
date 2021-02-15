package br.com.siberius.projeto.infrastructure.repository;

import br.com.siberius.projeto.domain.model.Consulta;
import br.com.siberius.projeto.domain.repository.filter.ConsultaFilter;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.From;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class ConsultaSpecs {

    public static Specification<Consulta> usandoFiltro(ConsultaFilter filtro) {
        return (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<Predicate>();

            From<?, ?> paciente = root.join("paciente", JoinType.INNER);
            From<?, ?> profissional = root.join("profissional", JoinType.INNER);

            if (filtro.getPaciente() != null) {
                predicates.add(builder.equal(root.get("paciente"), filtro.getPaciente()));
            }
            if (filtro.getProfissional() != null) {
                predicates.add(builder.equal(root.get("profissional"), filtro.getProfissional()));
            }
            if (filtro.getClinica() != null) {
                predicates.add(builder.equal(root.get("clinica"), filtro.getClinica()));
            }
            if (filtro.getId() != null) {
                predicates.add(builder.equal(root.get("id"), filtro.getId()));
            }

            if (filtro.getNomePaciente() != null) {
                predicates.add(builder.like(paciente.get("nome"), "%" + filtro.getNomePaciente() + "%"));
            }

            if (filtro.getNomeProfissional() != null) {
                predicates.add(builder.like(profissional.get("nome"), "%" + filtro.getNomeProfissional() + "%"));
            }

            if (filtro.getStatusConsultaEnum() != null) {
                predicates.add(builder.equal(root.get("statusConsultaEnum"), filtro.getStatusConsultaEnum()));
            }

            if (filtro.getDataInicio() != null) {
                predicates.add(builder.greaterThanOrEqualTo(root.get("start"),
                        filtro.getDataInicio()));
            }

            if (filtro.getDataFim() != null) {
                predicates.add(builder.lessThanOrEqualTo(root.get("start"),
                        filtro.getDataFim()));
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
