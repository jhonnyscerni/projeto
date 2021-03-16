package br.com.siberius.projeto.api.assembler.disassembler;

import br.com.siberius.projeto.api.model.input.FormaPagamentoInputModel;
import br.com.siberius.projeto.domain.model.FormaPagamento;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FormaPagamentoModelDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public FormaPagamento toDomainObject(FormaPagamentoInputModel formaPagamentoInputModel) {
        return modelMapper.map(formaPagamentoInputModel, FormaPagamento.class);
    }

    public void copyToDomainObject(FormaPagamentoInputModel formaPagamentoInputModel, FormaPagamento formaPagamento) {
        modelMapper.map(formaPagamentoInputModel, formaPagamento);
    }
}