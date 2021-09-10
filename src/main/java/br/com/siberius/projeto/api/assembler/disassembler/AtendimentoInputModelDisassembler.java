package br.com.siberius.projeto.api.assembler.disassembler;

import br.com.siberius.projeto.api.model.input.AtendimentoInputModel;
import br.com.siberius.projeto.domain.model.Atendimento;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AtendimentoInputModelDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Atendimento toDomainObject(AtendimentoInputModel atendimentoInputModel) {
        return modelMapper.map(atendimentoInputModel, Atendimento.class);
    }

    public void copyToDomainObject(AtendimentoInputModel atendimentoInputModel, Atendimento atendimento) {
        modelMapper.map(atendimentoInputModel, atendimento);
    }
}
