package br.com.siberius.projeto.domain.exception.model;

import br.com.siberius.projeto.domain.exception.EntidadeNaoEncontradaException;

public class GrupoNaoEncontradoException extends EntidadeNaoEncontradaException {

    private static final long serialVersionUID = 1L;
    public GrupoNaoEncontradoException(String message) {
        super(message);
    }

    public GrupoNaoEncontradoException(Long id) {
        this(String.format("Não existe um cadastro de grupo com código %d", id));
    }
}
