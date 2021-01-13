package br.com.siberius.projeto.domain.exception.model;

import br.com.siberius.projeto.domain.exception.EntidadeNaoEncontradaException;

public class AtendimentoNaoEncontradoException extends EntidadeNaoEncontradaException {

    public AtendimentoNaoEncontradoException(String message) {
        super(message);
    }

    public AtendimentoNaoEncontradoException(Long consultaId, Long atendimentoId) {
        this(String.format("Não existe um cadastro de consulta com código %d para o atendimento de código %d",
            consultaId, atendimentoId));
    }

    public AtendimentoNaoEncontradoException(Long id) {
        this(String.format("Não existe um cadastro de atendimento com código %d", id));
    }
}
