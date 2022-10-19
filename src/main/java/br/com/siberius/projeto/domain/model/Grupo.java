package br.com.siberius.projeto.domain.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "GRUPO")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Grupo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "COD_GRUPO")
    private Long id;

    @Column(name = "NM_GRUPO")
    private String nome;

    @ManyToMany
    @JoinTable(name = "GRUPO_PERMISSAO", joinColumns = @JoinColumn(name = "COD_GRUPO"),
        inverseJoinColumns = @JoinColumn(name = "COD_PERMISSAO"))
    private List<Permissao> permissoes = new ArrayList<>();

    public boolean removerPermissao(Permissao permissao) {
        return getPermissoes().remove(permissao);
    }

    public boolean adicionarPermissao(Permissao permissao) {
        return getPermissoes().add(permissao);
    }
}
