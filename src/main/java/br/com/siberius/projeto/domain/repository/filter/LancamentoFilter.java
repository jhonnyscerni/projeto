package br.com.siberius.projeto.domain.repository.filter;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LancamentoFilter {

    private Long id;

    private Long profissionalId;

    private Long pacienteId;

    private Long clinicaId;
}
