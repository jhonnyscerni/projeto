package br.com.siberius.projeto.domain.model;

import br.com.siberius.projeto.domain.model.enums.AtivadoStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.HashSet;
import java.util.Set;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Data
@Entity
@Table(name = "USUARIO")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Inheritance(
        strategy = InheritanceType.SINGLE_TABLE
)
@DiscriminatorValue("Admin")
@DiscriminatorColumn(
        name = "TP",
        discriminatorType = DiscriminatorType.STRING
)
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

    @Column(name = "CPF")
    private String cpf;

    @Column(name = "SEXO")
    private String sexo;

    @Column(name = "TELEFONE")
    private String telefone;

    @Column(name = "CELULAR")
    private String celular;

    @Column(name = "DT_NASCIMENTO")
    private OffsetDateTime dtNascimento;

    @Column(name = "SENHA")
    private String senha;

    @CreationTimestamp
    @Column(name = "DT_CAD_USUARIO", nullable = false, columnDefinition = "datetime")
    private OffsetDateTime dataCadastro;

    @Column(name = "ATIVADO")
    private String ativado;

    @ManyToMany( fetch=FetchType.LAZY)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinTable(name = "USUARIO_GRUPO", joinColumns = @JoinColumn(name = "COD_USUARIO"),
            inverseJoinColumns = @JoinColumn(name = "COD_GRUPO"))
    private Set<Grupo> grupos = new HashSet<>();

    @Column(name = "ENDERECO_CEP")
    private String cep;

    @Column(name = "ENDERECO_LOGRADOURO")
    private String logradouro;

    @Column(name = "ENDERECO_NUMERO")
    private String numero;

    @Column(name = "ENDERECO_COMPLEMENTO")
    private String complemento;

    @Column(name = "ENDERECO_BAIRRO")
    private String bairro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ENDERECO_CIDADE_ID")
    private Cidade cidade;

    public Usuario() {
        super();
        this.ativado = String.valueOf(AtivadoStatus.NAO);
    }

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

    public boolean isNovo() {
        return getId() == null;
    }

    public String getAtivado() {
        return ativado;
    }

    public void setAtivado(String ativado) {
        this.ativado = ativado;
    }

    @Transient
    public String getDiscriminatorValue() {
        DiscriminatorValue val = this.getClass().getAnnotation(DiscriminatorValue.class);

        return val == null ? null : val.value();
    }
}
