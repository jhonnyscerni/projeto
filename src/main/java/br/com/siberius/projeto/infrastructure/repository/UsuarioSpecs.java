package br.com.siberius.projeto.infrastructure.repository;

import br.com.siberius.projeto.domain.model.Usuario;
import br.com.siberius.projeto.domain.repository.filter.UsuarioFilter;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class UsuarioSpecs {

    public static Specification<Usuario> usandoFiltro(UsuarioFilter filtro) {
        return (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<Predicate>();

            root.fetch("cidade");
            root.fetch("cidade").fetch("estado");
            root.fetch("grupos");
            root.fetch("grupos").fetch("permissoes");

            if (filtro.getEmail() != null) {
                predicates.add(builder.like(root.get("email"), "%" + filtro.getEmail() + "%"));
            }

            if (filtro.getNome() != null) {
                predicates.add(builder.like(root.get("nome"), "%" + filtro.getNome() + "%"));
            }

            if (filtro.getDataCriacaoInicio() != null) {
                predicates.add(builder.greaterThanOrEqualTo(root.get("dataCriacao"),
                    filtro.getDataCriacaoInicio()));
            }

            if (filtro.getDataCriacaoFim() != null) {
                predicates.add(builder.lessThanOrEqualTo(root.get("dataCriacao"),
                    filtro.getDataCriacaoFim()));
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
