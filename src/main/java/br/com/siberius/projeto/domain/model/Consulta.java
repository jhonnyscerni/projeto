package br.com.siberius.projeto.domain.model;

import br.com.siberius.projeto.domain.model.enums.ConvenioEnum;
import br.com.siberius.projeto.domain.model.enums.ProcedimentoEnum;
import br.com.siberius.projeto.domain.model.enums.StatusConsultaEnum;
import java.time.OffsetDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "CONSULTA")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "COD_CONSULTA")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "PACIENTE")
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "PROFISSIONAL")
    private Profissional profissional;

    @ManyToOne
    @JoinColumn(name = "CLINICA")
    private Clinica clinica;

    @Column(name = "DATA_HORA")
    private OffsetDateTime dataHora;

    @Column(name = "LOCAL_ATENDIMENTO")
    private String localDeAtendimento;

    @Enumerated(EnumType.STRING)
    private ProcedimentoEnum procedimentoEnum;

    @Enumerated(EnumType.STRING)
    private StatusConsultaEnum statusConsultaEnum;

    @Enumerated(EnumType.STRING)
    private ConvenioEnum convenioEnum;

    public Consulta() {
        this.statusConsultaEnum = StatusConsultaEnum.AGENDADO;
    }

    @Column(name = "OBSERVACAO")
    private String observacoes;

    // Criando Integração com FullCalendar

    @Column(name = "START")
    private OffsetDateTime start;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "CLASS_NAME")
    private String className;
}
