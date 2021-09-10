package br.com.siberius.projeto.domain.service;

import br.com.siberius.projeto.domain.exception.EntidadeEmUsoException;
import br.com.siberius.projeto.domain.exception.model.GrupoNaoEncontradoException;
import br.com.siberius.projeto.domain.exception.model.UsuarioNaoEncontradoException;
import br.com.siberius.projeto.domain.model.Grupo;
import br.com.siberius.projeto.domain.model.Permissao;
import br.com.siberius.projeto.domain.repository.GrupoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class GrupoService {

    private static final String MSG_USUARIO_EM_USO
        = "Grupo de código %d não pode ser removida, pois está em uso";

    @Autowired
    private GrupoRepository grupoRepository;

    @Autowired
    private PermissaoService permissaoService;

    public Grupo buscarOuFalhar(Long grupoId) {
        return grupoRepository.findById(grupoId).orElseThrow(() -> new GrupoNaoEncontradoException(grupoId));
    }

    public Grupo salvar(Grupo grupo) {
        return grupoRepository.save(grupo);
    }

    public void excluir(Long grupoId) {
        try {
            grupoRepository.deleteById(grupoId);
        } catch (EmptyResultDataAccessException e) {
            throw new UsuarioNaoEncontradoException(grupoId);

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                String.format(MSG_USUARIO_EM_USO, grupoId));
        }
    }

    public void associarPermissao(Long grupoId, Long permissaoId) {
        Grupo grupo = buscarOuFalhar(grupoId);
        Permissao permissao = permissaoService.buscarOuFalhar(permissaoId);

        grupo.adicionarPermissao(permissao);
        grupoRepository.save(grupo);
    }

    public void desassociarPermissao(Long grupoId, Long permissaoId) {
        Grupo grupo = buscarOuFalhar(grupoId);
        Permissao permissao = permissaoService.buscarOuFalhar(permissaoId);

        grupo.removerPermissao(permissao);
        grupoRepository.save(grupo);
    }
}
