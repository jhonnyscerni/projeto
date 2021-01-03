package br.com.siberius.projeto.domain.model;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

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
    private Date dtNascimento;

    @Column(name = "SENHA")
    private String senha;

    @CreationTimestamp
    @Column(name = "DT_CAD_USUARIO", nullable = false, columnDefinition = "datetime")
    private OffsetDateTime dataCadastro;

    @Column(name = "ATIVADO")
    private boolean ativado;

    @ManyToMany()
    @JoinTable(name = "USUARIO_GRUPO", joinColumns = @JoinColumn(name = "COD_USUARIO"),
        inverseJoinColumns = @JoinColumn(name = "COD_GRUPO"))
    private List<Grupo> grupos = new ArrayList<>();

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

    @ManyToOne
    @JoinColumn(name = "ENDERECO_CIDADE_ID")
    private Cidade cidade;

    public Usuario() {
        super();
        this.ativado = false;
    }

    public Usuario(Long id, String nome, String email, String senha, boolean ativado) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.ativado = ativado;
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

    public boolean isAtivado() {
        return ativado;
    }

    public void setAtivado(final boolean ativado) {
        this.ativado = ativado;
    }

    @Transient
    public String getDiscriminatorValue(){
        DiscriminatorValue val = this.getClass().getAnnotation( DiscriminatorValue.class );

        return val == null ? null : val.value();
    }
}
