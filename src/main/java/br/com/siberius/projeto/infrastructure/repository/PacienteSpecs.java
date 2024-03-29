package br.com.siberius.projeto.infrastructure.repository;

import br.com.siberius.projeto.domain.model.Paciente;
import br.com.siberius.projeto.domain.repository.filter.PacienteFilter;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class PacienteSpecs {

    public static Specification<Paciente> usandoFiltro(PacienteFilter filtro) {
        return (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<Predicate>();

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

            if (filtro.getProfissionalId() != null) {
                predicates.add(builder.equal(root.get("profissionalId"), filtro.getProfissionalId()));
            }

            if (filtro.getClinicaId() != null) {
                predicates.add(builder.equal(root.get("clinicaId"), filtro.getClinicaId()));
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
