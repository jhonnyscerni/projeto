package br.com.siberius.projeto.domain.service;

import br.com.siberius.projeto.domain.exception.EntidadeEmUsoException;
import br.com.siberius.projeto.domain.exception.model.PermissaoNaoEncontradoException;
import br.com.siberius.projeto.domain.exception.model.UsuarioNaoEncontradoException;
import br.com.siberius.projeto.domain.model.Permissao;
import br.com.siberius.projeto.domain.repository.PermissaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class PermissaoService {

    private static final String MSG_PERMISSAO_EM_USO
        = "Usuário de código %d não pode ser removida, pois está em uso";

    @Autowired
    private PermissaoRepository permissaoRepository;


    public Permissao salvar(Permissao permissao) {
        return permissaoRepository.save(permissao);
    }

    public Permissao buscarOuFalhar(Long permissaoId) {
        return permissaoRepository.findById(permissaoId).orElseThrow(() -> new PermissaoNaoEncontradoException(permissaoId));
    }

    public void excluir(Long permissaoId) {
        try {
            permissaoRepository.deleteById(permissaoId);
        } catch (EmptyResultDataAccessException e) {
            throw new UsuarioNaoEncontradoException(permissaoId);

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                String.format(MSG_PERMISSAO_EM_USO, permissaoId));
        }
    }
}
