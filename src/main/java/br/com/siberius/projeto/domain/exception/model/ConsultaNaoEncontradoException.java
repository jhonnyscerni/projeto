package br.com.siberius.projeto.domain.exception.model;

import br.com.siberius.projeto.domain.exception.EntidadeNaoEncontradaException;

public class ConsultaNaoEncontradoException extends EntidadeNaoEncontradaException {

    public ConsultaNaoEncontradoException(String message) {
        super(message);
    }

    public ConsultaNaoEncontradoException(Long id) {
        this(String.format("Não existe um cadastro da consulta com código %d", id));
    }
}
