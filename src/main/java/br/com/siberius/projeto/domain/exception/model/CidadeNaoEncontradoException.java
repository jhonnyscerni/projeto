package br.com.siberius.projeto.domain.exception.model;

import br.com.siberius.projeto.domain.exception.EntidadeNaoEncontradaException;

public class CidadeNaoEncontradoException extends EntidadeNaoEncontradaException {

    public CidadeNaoEncontradoException(String message) {
        super(message);
    }

    public CidadeNaoEncontradoException(Long id) {
        this(String.format("Não existe um cadastro de cidade com código %d", id));
    }
}
