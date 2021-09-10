package br.com.siberius.projeto.domain.repository;

import br.com.siberius.projeto.domain.model.dto.EstatisticaSexo;
import br.com.siberius.projeto.infrastructure.service.filter.EstatisticaSexoFilter;

import java.util.List;

public interface SexoConsultaQueryService {

    List<EstatisticaSexo> estatisticaSexo(EstatisticaSexoFilter filter, String timeOffset);
}
