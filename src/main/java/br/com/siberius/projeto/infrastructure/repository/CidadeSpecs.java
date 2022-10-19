package br.com.siberius.projeto.infrastructure.repository;

import br.com.siberius.projeto.domain.model.Cidade;
import br.com.siberius.projeto.domain.model.Paciente;
import br.com.siberius.projeto.domain.repository.filter.PacienteFilter;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class CidadeSpecs {
    public static Specification<Cidade> usandoFiltroEstadoId(Long estadoId) {
        return (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<Predicate>();

            root.fetch("estado");

            if (estadoId != null) {
                predicates.add(builder.equal(root.get("estado"), estadoId));
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
