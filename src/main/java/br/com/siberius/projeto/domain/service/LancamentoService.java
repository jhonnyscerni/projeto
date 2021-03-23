package br.com.siberius.projeto.domain.service;

import br.com.siberius.projeto.domain.exception.EntidadeEmUsoException;
import br.com.siberius.projeto.domain.exception.model.AtendimentoNaoEncontradoException;
import br.com.siberius.projeto.domain.exception.model.LancamentoNaoEncontradoException;
import br.com.siberius.projeto.domain.model.Lancamento;
import br.com.siberius.projeto.domain.repository.LancamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class LancamentoService {

    private static final String MSG_LANCAMENTO_EM_USO
        = "Lançamento de código %d não pode ser removida, pois está em uso";

    @Autowired
    private LancamentoRepository lancamentoRepository;

    public Lancamento buscarOuFalhar(Long lancamentoId) {
        return lancamentoRepository.findById(lancamentoId).orElseThrow(() -> new LancamentoNaoEncontradoException(lancamentoId));
    }

    public Lancamento salvar(Lancamento lancamento) {
        return lancamentoRepository.save(lancamento);
    }

    public void excluir(Long lancamentoId) {
        try {
            lancamentoRepository.deleteById(lancamentoId);
            lancamentoRepository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new AtendimentoNaoEncontradoException(lancamentoId);
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                String.format(MSG_LANCAMENTO_EM_USO, lancamentoId));
        }
    }
}
