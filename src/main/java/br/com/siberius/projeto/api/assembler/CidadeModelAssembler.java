package br.com.siberius.projeto.api.assembler;

import br.com.siberius.projeto.api.model.CidadeModel;
import br.com.siberius.projeto.domain.model.Cidade;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CidadeModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public CidadeModel toModel(Cidade cidade) {
        return modelMapper.map(cidade, CidadeModel.class);
    }

    public List<CidadeModel> toCollectionModel(Collection<Cidade> cidades) {
        return cidades.stream().map(cidade -> toModel(cidade)).collect(Collectors.toList());
    }


}
