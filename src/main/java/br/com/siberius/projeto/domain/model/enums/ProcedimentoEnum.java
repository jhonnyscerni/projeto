package br.com.siberius.projeto.domain.model.enums;

public enum ProcedimentoEnum {

    CONSULTA("Consulta");

    private String descricao;

    ProcedimentoEnum(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
