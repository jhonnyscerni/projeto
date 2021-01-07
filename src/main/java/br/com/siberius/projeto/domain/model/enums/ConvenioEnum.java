package br.com.siberius.projeto.domain.model.enums;

public enum ConvenioEnum {

    PARTICULAR("Particular"),
    CONVENIO("Convênio");

    private String descricao;

    ConvenioEnum(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
