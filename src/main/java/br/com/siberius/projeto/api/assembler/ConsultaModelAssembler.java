package br.com.siberius.projeto.api.assembler;

import br.com.siberius.projeto.api.model.ConsultaModel;
import br.com.siberius.projeto.domain.model.Consulta;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsultaModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public ConsultaModel toModel(Consulta consulta) {
        return modelMapper.map(consulta, ConsultaModel.class);
    }

    public List<ConsultaModel> toCollectionModel(List<Consulta> consultas) {
        return consultas.stream().map(consulta -> toModel(consulta)).collect(Collectors.toList());
    }


}
