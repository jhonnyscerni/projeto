package br.com.siberius.projeto.domain.model.dto;

import br.com.siberius.projeto.domain.model.enums.StatusConsultaEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class EstatisticaStatus {

//    private Date data;

    private StatusConsultaEnum name;

    private Long value;

}
