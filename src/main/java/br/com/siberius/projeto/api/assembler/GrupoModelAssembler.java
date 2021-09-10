package br.com.siberius.projeto.api.assembler;

import br.com.siberius.projeto.api.model.GrupoModel;
import br.com.siberius.projeto.core.security.authorizationserver.userdetails.GrupoModelCustomClaims;
import br.com.siberius.projeto.domain.model.Grupo;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GrupoModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public GrupoModel toModel(Grupo grupo) {
        return modelMapper.map(grupo, GrupoModel.class);
    }

    public GrupoModelCustomClaims toModelCustomClaims(Grupo grupo) {
        return modelMapper.map(grupo, GrupoModelCustomClaims.class);
    }

    public List<GrupoModel> toCollectionModel(Collection<Grupo> grupos) {
        return grupos.stream().map(grupo -> toModel(grupo)).collect(Collectors.toList());
    }




}
