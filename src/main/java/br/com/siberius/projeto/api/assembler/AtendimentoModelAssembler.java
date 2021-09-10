package br.com.siberius.projeto.api.assembler;

import br.com.siberius.projeto.api.model.AtendimentoModel;
import br.com.siberius.projeto.api.model.CidadeModel;
import br.com.siberius.projeto.domain.model.Atendimento;
import br.com.siberius.projeto.domain.model.Cidade;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AtendimentoModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public AtendimentoModel toModel(Atendimento atendimento) {
        return modelMapper.map(atendimento, AtendimentoModel.class);
    }

    public List<AtendimentoModel> toCollectionModel(Collection<Atendimento> atendimentos) {
        return atendimentos.stream().map(atendimento -> toModel(atendimento)).collect(Collectors.toList());
    }


}
