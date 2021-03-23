package br.com.siberius.projeto.domain.exception.model;

import br.com.siberius.projeto.domain.exception.EntidadeNaoEncontradaException;

public class LancamentoNaoEncontradoException extends EntidadeNaoEncontradaException {

    private static final long serialVersionUID = 1L;

    public LancamentoNaoEncontradoException(String message) {
        super(message);
    }

    public LancamentoNaoEncontradoException(Long lancamentoId) {
        this(String.format("Não existe cadastro de lançamento com código %d", lancamentoId));
    }
}