package br.com.siberius.projeto.domain.repository.filter;

import br.com.siberius.projeto.domain.model.Consulta;
import br.com.siberius.projeto.domain.model.Paciente;
import br.com.siberius.projeto.domain.model.Profissional;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AtendimentoFilter {

    private Long id;

    private Long profissionalId;

    private Long pacienteId;

    private String nomePaciente;

    private String emailPaciente;

    private Long clinicaId;
}
