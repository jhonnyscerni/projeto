package br.com.siberius.projeto.infrastructure.service;

import br.com.siberius.projeto.domain.model.Paciente;
import br.com.siberius.projeto.domain.model.dto.EstatisticaSexo;
import br.com.siberius.projeto.domain.repository.SexoConsultaQueryService;
import br.com.siberius.projeto.infrastructure.service.filter.EstatisticaSexoFilter;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SexoConsultaQueryServiceImpl implements SexoConsultaQueryService {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<EstatisticaSexo> estatisticaSexo(EstatisticaSexoFilter filter, String timeOffset) {

        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<EstatisticaSexo> query = builder.createQuery(EstatisticaSexo.class);
        Root<Paciente> root = query.from(Paciente.class);
        ArrayList<Predicate> predicates = new ArrayList<Predicate>();

        CompoundSelection<EstatisticaSexo> selection = builder.construct(EstatisticaSexo.class,
                root.get("sexo"),
                builder.count(root.get("sexo")));

        if (filter.getProfissionalId() != null) {
            predicates.add(builder.equal(root.get("profissionalId"), filter.getProfissionalId()));
        }

        System.out.println(OffsetDateTime.now());

//        predicates.add(builder.greaterThanOrEqualTo(root.get("start"),
//                    OffsetDateTime.now()));
//

        query.select(selection);
        query.where(predicates.toArray(new Predicate[0]));
//        query.groupBy(functionDateDataCriacao , root.get("statusConsultaEnum"));
        query.groupBy(root.get("sexo"));


        return manager.createQuery(query).getResultList();

    }
}
