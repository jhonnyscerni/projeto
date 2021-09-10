package br.com.siberius.projeto.api.assembler;


import br.com.siberius.projeto.api.model.FormaPagamentoModel;
import br.com.siberius.projeto.domain.model.FormaPagamento;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FormaPagamentoModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public FormaPagamentoModel toModel(FormaPagamento formaPagamento) {
        return modelMapper.map(formaPagamento, FormaPagamentoModel.class);
    }

    public List<FormaPagamentoModel> toCollectionModel(Collection<FormaPagamento> formasPagamento) {
        return formasPagamento.stream()
            .map(formaPagamento -> toModel(formaPagamento))
            .collect(Collectors.toList());
    }

}