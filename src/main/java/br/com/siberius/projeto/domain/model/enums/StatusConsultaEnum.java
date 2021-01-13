package br.com.siberius.projeto.domain.model.enums;

public enum StatusConsultaEnum {

    AGENDADO("Agendado"),
    CONFIRMADO("Confirmado"),
    CANCELADO("Cancelado"),
    REAGENDADO("ReAgendado"),
    FINALIZADO("Finalizado");

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
