package br.com.siberius.projeto.domain.repository;

import br.com.siberius.projeto.domain.model.dto.EstatisticaStatus;
import br.com.siberius.projeto.infrastructure.service.filter.EstatisticaStatusFilter;

import java.util.List;

public interface StatusConsultaQueryService {

    List<EstatisticaStatus> estatisticaStatus(EstatisticaStatusFilter filter, String timeOffset);
}
