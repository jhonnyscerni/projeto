package br.com.siberius.projeto.api.assembler;

import br.com.siberius.projeto.api.model.CategoriaLancamentoModel;
import br.com.siberius.projeto.domain.model.CategoriaLancamento;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CategoriaLancamentoModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public CategoriaLancamentoModel toModel(CategoriaLancamento categoriaLancamento) {
        return modelMapper.map(categoriaLancamento, CategoriaLancamentoModel.class);
    }

    public List<CategoriaLancamentoModel> toCollectionModel(Collection<CategoriaLancamento> categoriaLancamentos) {
        return categoriaLancamentos.stream().map(categoriaLancamento -> toModel(categoriaLancamento)).collect(Collectors.toList());
    }
}
