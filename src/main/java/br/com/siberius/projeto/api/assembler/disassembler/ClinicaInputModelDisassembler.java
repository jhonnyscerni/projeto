package br.com.siberius.projeto.api.assembler.disassembler;

import br.com.siberius.projeto.api.model.input.ClinicaInputComSenhaModel;
import br.com.siberius.projeto.api.model.input.ClinicaInputModel;
import br.com.siberius.projeto.api.model.input.ProfissionalInputComSenhaModel;
import br.com.siberius.projeto.domain.model.Clinica;
import br.com.siberius.projeto.domain.model.Profissional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClinicaInputModelDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Clinica toDomainObject(ClinicaInputModel clinicaInputModel) {
        return modelMapper.map(clinicaInputModel, Clinica.class);
    }

    public Clinica toDomainObjectComSenha(ClinicaInputComSenhaModel clinicaInputComSenhaModel) {
        return modelMapper.map(clinicaInputComSenhaModel, Clinica.class);
    }

    public void copyToDomainObject(ClinicaInputModel clinicaInputModel, Clinica clinica) {
        modelMapper.map(clinicaInputModel, clinica);
    }
}
