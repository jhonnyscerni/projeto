package br.com.siberius.projeto.api.assembler.disassembler;

import br.com.siberius.projeto.api.model.input.ProfissionalInputComSenhaModel;
import br.com.siberius.projeto.api.model.input.ProfissionalInputModel;
import br.com.siberius.projeto.domain.model.Profissional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProfissionalInputModelDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Profissional toDomainObject(ProfissionalInputModel profissionalInputModel) {
        return modelMapper.map(profissionalInputModel, Profissional.class);
    }

    public Profissional toDomainObjectComSenha(ProfissionalInputComSenhaModel profissionalInputComSenhaModel) {
        return modelMapper.map(profissionalInputComSenhaModel, Profissional.class);
    }

    public void copyToDomainObject(ProfissionalInputModel profissionalInputModel, Profissional profissional) {
        modelMapper.map(profissionalInputModel, profissional);
    }
}
