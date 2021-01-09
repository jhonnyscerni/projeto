package br.com.siberius.projeto.api.assembler.disassembler;

import br.com.siberius.projeto.api.model.input.ConsultaInputModel;
import br.com.siberius.projeto.api.model.input.PacienteInputComSenhaModel;
import br.com.siberius.projeto.domain.model.Consulta;
import br.com.siberius.projeto.domain.model.Paciente;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsultaInputModelDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Consulta toDomainObject(ConsultaInputModel consultaInputModel) {
        return modelMapper.map(consultaInputModel, Consulta.class);
    }

    public Consulta toDomainObjectConsulta(ConsultaInputModel consultaInputModel) {
        return modelMapper.map(consultaInputModel, Consulta.class);
    }

    public void copyToDomainObject(ConsultaInputModel consultaInputModel, Consulta consulta) {
        modelMapper.map(consultaInputModel, consulta);
    }
}
