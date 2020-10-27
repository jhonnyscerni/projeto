package br.com.siberius.projeto.domain.service;

import br.com.siberius.projeto.domain.exception.model.PermissaoNaoEncontradoException;
import br.com.siberius.projeto.domain.model.Permissao;
import br.com.siberius.projeto.domain.repository.PermissaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissaoService {

    @Autowired
    private PermissaoRepository permissaoRepository;


    public Permissao salvar(Permissao permissao) {
        return permissaoRepository.save(permissao);
    }

    public Permissao buscarOuFalhar(Long permissaoId) {
        return permissaoRepository.findById(permissaoId).orElseThrow(() -> new PermissaoNaoEncontradoException(permissaoId));
    }
}
