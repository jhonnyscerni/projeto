package br.com.siberius.projeto.infrastructure.service;

import br.com.siberius.projeto.domain.model.Consulta;
import br.com.siberius.projeto.domain.model.dto.EstatisticaStatus;
import br.com.siberius.projeto.domain.repository.StatusConsultaQueryService;
import br.com.siberius.projeto.infrastructure.service.filter.EstatisticaStatusFilter;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class StatusConsultaQueryServiceImpl implements StatusConsultaQueryService {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<EstatisticaStatus> estatisticaStatus(EstatisticaStatusFilter filter,  String timeOffset) {

        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<EstatisticaStatus> query = builder.createQuery(EstatisticaStatus.class);
        Root<Consulta> root = query.from(Consulta.class);
        ArrayList<Predicate> predicates = new ArrayList<Predicate>();

//        Expression<OffsetDateTime> functionConvertTzDataCriacao = builder.function(
//                "convert_tz", OffsetDateTime.class, root.get("start"),
//                builder.literal("+00:00"), builder.literal(timeOffset));
//
//        Expression<Date> functionDateDataCriacao = builder.function(
//                "date", Date.class, functionConvertTzDataCriacao);

        CompoundSelection<EstatisticaStatus> selection = builder.construct(EstatisticaStatus.class,
//                functionDateDataCriacao,
                root.get("statusConsultaEnum"),
                builder.count(root.get("statusConsultaEnum")));

        if (filter.getProfissionalId() != null) {
            predicates.add(builder.equal(root.get("profissional"), filter.getProfissionalId()));
        }

        System.out.println(OffsetDateTime.now());

//        predicates.add(builder.greaterThanOrEqualTo(root.get("start"),
//                    OffsetDateTime.now()));
//

        query.select(selection);
        query.where(predicates.toArray(new Predicate[0]));
//        query.groupBy(functionDateDataCriacao , root.get("statusConsultaEnum"));
        query.groupBy(root.get("statusConsultaEnum"));


        return manager.createQuery(query).getResultList();

    }
}
