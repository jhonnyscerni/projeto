package br.com.siberius.projeto.api.model.input;

import javax.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PermissaoInputModel {

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String descricao;
}
