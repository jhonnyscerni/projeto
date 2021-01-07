package br.com.siberius.projeto.domain.model.enums;

public enum StatusConsultaEnum {

    AGENDADO("Agendado"),
    CONFIRMADO("Confirmado"),
    ESPERANDO("Esperando"),
    CANCELADO("Cancelado"),
    NAO_COMPARECEU("Não Compareceu"),
    REAGENDADO("ReAgendado"),
    EM_ATENDIMENTO("Em Atendimento"),
    FINALIZADO("Finalizado"),
    BLOQUEADO("Bloquado");

    private String descricao;

    StatusConsultaEnum(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
