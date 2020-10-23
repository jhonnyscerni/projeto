package br.com.siberius.projeto.domain.exception;

public class EntidadeEmUsoException extends NegocioException {

    private static final long serialVersionUID = 1L;

    public EntidadeEmUsoException(String message) {
        super(message);
    }

    public EntidadeEmUsoException(String message, Throwable cause) {
        super(message, cause);
    }
}
