package br.com.siberius.projeto.api.assembler.disassembler;

import br.com.siberius.projeto.api.model.input.GrupoInputModel;
import br.com.siberius.projeto.domain.model.Grupo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GrupoInputModelDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Grupo toDomainObject(GrupoInputModel grupoInputModel) {
        return modelMapper.map(grupoInputModel, Grupo.class);
    }

    public void copyToDomainObject(GrupoInputModel grupoInputModel, Grupo grupo) {
        modelMapper.map(grupoInputModel, grupo);
    }
}
