package br.com.siberius.projeto.domain.exception.model;

import br.com.siberius.projeto.domain.exception.EntidadeNaoEncontradaException;

public class ClinicaNaoEncontradoException extends EntidadeNaoEncontradaException {

    public ClinicaNaoEncontradoException(String message) {
        super(message);
    }

    public ClinicaNaoEncontradoException(Long id) {
        this(String.format("Não existe um cadastro de clinica com código %d", id));
    }
}
