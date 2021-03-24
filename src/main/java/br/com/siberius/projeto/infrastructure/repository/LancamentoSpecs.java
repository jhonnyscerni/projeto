package br.com.siberius.projeto.infrastructure.repository;

import br.com.siberius.projeto.domain.model.Lancamento;
import br.com.siberius.projeto.domain.repository.filter.LancamentoFilter;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class LancamentoSpecs {


    public static Specification<Lancamento> usandoFiltro(LancamentoFilter filtro) {
        return (root, query, builder) -> {

            List<Predicate> predicates = new ArrayList<Predicate>();

            if (filtro.getProfissionalId() != null) {
                predicates.add(builder.equal(root.get("profissionalId"), filtro.getProfissionalId()));
            }

            if (filtro.getClinicaId() != null) {
                predicates.add(builder.equal(root.get("clinicaId"), filtro.getClinicaId()));
            }

            if (filtro.getId() != null) {
                predicates.add(builder.equal(root.get("id"), filtro.getId()));
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
