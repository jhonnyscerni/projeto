package br.com.siberius.projeto.domain.exception.model;

import br.com.siberius.projeto.domain.exception.EntidadeNaoEncontradaException;

public class PermissaoNaoEncontradoException extends EntidadeNaoEncontradaException {

    public PermissaoNaoEncontradoException(String message) {
        super(message);
    }

    public PermissaoNaoEncontradoException(Long id) {
        this(String.format("Não existe um cadastro de permissão com código %d", id));
    }
}
