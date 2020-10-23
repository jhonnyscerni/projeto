package br.com.siberius.projeto.api.assembler;

import br.com.siberius.projeto.api.model.UsuarioModel;
import br.com.siberius.projeto.domain.model.Usuario;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsuarioModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public UsuarioModel toModel(Usuario usuario) {
        return modelMapper.map(usuario, UsuarioModel.class);
    }

    public List<UsuarioModel> toCollectionModel(List<Usuario> usuarios) {
        return usuarios.stream().map(usuario -> toModel(usuario)).collect(Collectors.toList());
    }


}
