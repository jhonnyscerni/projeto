package br.com.siberius.projeto.infrastructure.service.filter;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstatisticaSexoFilter {

    private Long profissionalId;

    private Long clinicaId;

//    @DateTimeFormat(iso = ISO.DATE_TIME)
//    private OffsetDateTime data;

}
