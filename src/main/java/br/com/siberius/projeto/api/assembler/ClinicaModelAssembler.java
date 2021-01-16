package br.com.siberius.projeto.api.assembler;

import br.com.siberius.projeto.api.model.ClinicaModel;
import br.com.siberius.projeto.api.model.UsuarioModel;
import br.com.siberius.projeto.domain.model.Clinica;
import br.com.siberius.projeto.domain.model.Usuario;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClinicaModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public ClinicaModel toModel(Clinica clinica) {
        return modelMapper.map(clinica, ClinicaModel.class);
    }

    public List<ClinicaModel> toCollectionModel(List<Clinica> clinicas) {
        return clinicas.stream().map(clinica -> toModel(clinica)).collect(Collectors.toList());
    }


}
