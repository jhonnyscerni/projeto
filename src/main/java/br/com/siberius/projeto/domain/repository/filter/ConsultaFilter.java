package br.com.siberius.projeto.domain.repository.filter;

import br.com.siberius.projeto.domain.model.Clinica;
import br.com.siberius.projeto.domain.model.Paciente;
import br.com.siberius.projeto.domain.model.Profissional;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ConsultaFilter {

    private Long id;

    private Paciente paciente;

    private Profissional profissional;

    private Clinica clinica;
}
