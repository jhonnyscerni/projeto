package br.com.siberius.projeto.api.assembler;

import br.com.siberius.projeto.api.model.ProfissionalModel;
import br.com.siberius.projeto.domain.model.Profissional;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProfissionalModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public ProfissionalModel toModel(Profissional profissional) {
        return modelMapper.map(profissional, ProfissionalModel.class);
    }

    public List<ProfissionalModel> toCollectionModel(List<Profissional> profissionalList) {
        return profissionalList.stream().map(profissional -> toModel(profissional)).collect(Collectors.toList());
    }
}
