package br.com.siberius.projeto.api.assembler.disassembler;

import br.com.siberius.projeto.api.model.input.PacienteInputComSenhaModel;
import br.com.siberius.projeto.api.model.input.PacienteInputModel;
import br.com.siberius.projeto.domain.model.Paciente;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PacienteInputModelDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Paciente toDomainObject(PacienteInputModel pacienteInputModel) {
        return modelMapper.map(pacienteInputModel, Paciente.class);
    }

    public Paciente toDomainObjectComSenha(PacienteInputComSenhaModel pacienteInputComSenhaModel) {
        return modelMapper.map(pacienteInputComSenhaModel, Paciente.class);
    }

    public void copyToDomainObject(PacienteInputModel pacienteInputModel, Paciente paciente) {
        modelMapper.map(pacienteInputModel, paciente);
    }
}
