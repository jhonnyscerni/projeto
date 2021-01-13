package br.com.siberius.projeto.infrastructure.repository;

import br.com.siberius.projeto.domain.model.Atendimento;
import br.com.siberius.projeto.domain.repository.filter.AtendimentoFilter;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class AtendimentoSpecs {

    public static Specification<Atendimento> usandoFiltro(AtendimentoFilter filtro) {
        return (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<Predicate>();

            if (filtro.getConsulta().getPaciente() != null) {
                predicates.add(builder.equal(root.get("consulta.paciente"), filtro.getConsulta().getPaciente()));
            }
            if (filtro.getConsulta().getProfissional() != null) {
                predicates.add(builder.equal(root.get("consulta.profissional"), filtro.getConsulta().getProfissional()));
            }
            if (filtro.getId() != null) {
                predicates.add(builder.equal(root.get("id"), filtro.getId()));
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
