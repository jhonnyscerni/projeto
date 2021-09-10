package br.com.siberius.projeto.api.assembler;

import br.com.siberius.projeto.api.model.LancamentoModel;
import br.com.siberius.projeto.domain.model.Lancamento;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LancamentoModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public LancamentoModel toModel(Lancamento lancamento) {
        return modelMapper.map(lancamento, LancamentoModel.class);
    }

    public List<LancamentoModel> toCollectionModel(Collection<Lancamento> lancamentos) {
        return lancamentos.stream().map(lancamento -> toModel(lancamento)).collect(Collectors.toList());
    }
}
