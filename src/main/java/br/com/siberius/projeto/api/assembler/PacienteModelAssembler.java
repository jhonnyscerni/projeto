package br.com.siberius.projeto.api.assembler;

import br.com.siberius.projeto.api.model.PacienteModel;
import br.com.siberius.projeto.api.model.ProfissionalModel;
import br.com.siberius.projeto.domain.model.Paciente;
import br.com.siberius.projeto.domain.model.Profissional;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PacienteModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public PacienteModel toModel(Paciente paciente) {
        return modelMapper.map(paciente, PacienteModel.class);
    }

    public List<PacienteModel> toCollectionModel(List<Paciente> pacienteList) {
        return pacienteList.stream().map(paciente -> toModel(paciente)).collect(Collectors.toList());
    }
}
