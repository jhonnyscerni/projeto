package br.com.siberius.projeto.domain.repository.filter;

import br.com.siberius.projeto.domain.model.Clinica;
import br.com.siberius.projeto.domain.model.Paciente;
import br.com.siberius.projeto.domain.model.Profissional;
import br.com.siberius.projeto.domain.model.enums.StatusConsultaEnum;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.OffsetDateTime;

@Setter
@Getter
public class ConsultaFilter {

    private Long id;

    private Paciente paciente;

    private String nomePaciente;

    private String nomeProfissional;

    private Profissional profissional;

    private Clinica clinica;

    @Enumerated(EnumType.STRING)
    private StatusConsultaEnum statusConsultaEnum;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private OffsetDateTime dataInicio;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private OffsetDateTime dataFim;
}
