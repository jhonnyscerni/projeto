package br.com.siberius.projeto.api.assembler.disassembler;

import br.com.siberius.projeto.api.model.input.LancamentoInputModel;
import br.com.siberius.projeto.domain.model.Lancamento;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LancamentoInputModelDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Lancamento toDomainObject(LancamentoInputModel lancamentoInputModel) {
        return modelMapper.map(lancamentoInputModel, Lancamento.class);
    }

    public void copyToDomainObject(LancamentoInputModel lancamentoInputModel, Lancamento lancamento) {
        modelMapper.map(lancamentoInputModel, lancamento);
    }
}
