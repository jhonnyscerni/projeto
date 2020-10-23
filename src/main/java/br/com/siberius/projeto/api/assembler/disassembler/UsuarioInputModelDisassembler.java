package br.com.siberius.projeto.api.assembler.disassembler;

import br.com.siberius.projeto.api.model.input.UsuarioInputModel;
import br.com.siberius.projeto.domain.model.Usuario;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsuarioInputModelDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Usuario toDomainObject(UsuarioInputModel usuarioInputModel) {
        return modelMapper.map(usuarioInputModel, Usuario.class);
    }

    public void copyToDomainObject(UsuarioInputModel usuarioInputModel, Usuario usuario) {
        modelMapper.map(usuarioInputModel, usuario);
    }
}
