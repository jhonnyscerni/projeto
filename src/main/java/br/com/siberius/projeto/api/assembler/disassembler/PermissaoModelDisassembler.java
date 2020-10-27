package br.com.siberius.projeto.api.assembler.disassembler;

import br.com.siberius.projeto.api.model.input.PermissaoInputModel;
import br.com.siberius.projeto.domain.model.Permissao;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PermissaoModelDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Permissao toDomainObject(PermissaoInputModel permissaoInputModel) {
        return modelMapper.map(permissaoInputModel, Permissao.class);
    }

    public void copyToDomainObject(PermissaoInputModel permissaoInputModel, Permissao permissao) {
        modelMapper.map(permissaoInputModel, permissao);
    }
}
