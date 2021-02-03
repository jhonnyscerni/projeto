package br.com.siberius.projeto.infrastructure.service.filter;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import java.time.OffsetDateTime;

@Getter
@Setter
public class EstatisticaStatusFilter {

    private Long profissionalId;

//    @DateTimeFormat(iso = ISO.DATE_TIME)
//    private OffsetDateTime data;

}
