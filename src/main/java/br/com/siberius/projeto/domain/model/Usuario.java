package br.com.siberius.projeto.domain.model;

import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;
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
import org.hibernate.annotations.CreationTimestamp;

@Data
@Entity
@Table(name = "USUARIO")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "COD_USUARIO")
    private Long id;

    @Column(name = "NM_USUARIO")
    private String nome;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "SENHA")
    private String senha;

    @CreationTimestamp
    @Column(name = "DT_CAD_USUARIO", nullable = false, columnDefinition = "datetime")
    private OffsetDateTime dataCadastro;

    @ManyToMany
    @JoinTable(name = "USUARIO_GRUPO", joinColumns = @JoinColumn(name = "COD_USUARIO"),
        inverseJoinColumns = @JoinColumn(name = "COD_GRUPO"))
    private Set<Grupo> grupos = new HashSet<>();

    public boolean adicionarGrupo(Grupo grupo) {
        return getGrupos().add(grupo);
    }

    public boolean removerGrupo(Grupo grupo) {
        return getGrupos().remove(grupo);
    }

    public boolean senhaCoincideCom(String senha) {
        return getSenha().equals(senha);
    }

    public boolean senhaNaoCoincideCom(String senha) {
        return !senhaCoincideCom(senha);
    }
}
