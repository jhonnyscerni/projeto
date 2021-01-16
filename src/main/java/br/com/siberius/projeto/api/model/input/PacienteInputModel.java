package br.com.siberius.projeto.api.model.input;

import br.com.siberius.projeto.api.model.CidadeModel;
import br.com.siberius.projeto.api.model.GrupoModel;
import br.com.siberius.projeto.api.model.ProfissionalModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PacienteInputModel {

    @ApiModelProperty(example = "1")
    private Long id;

    @ApiModelProperty(example = "Jhonny Scerni", required = true)
    @NotBlank
    private String nome;

    @ApiModelProperty(example = "jhonnyscerni@gmail.com", required = true)
    @NotBlank
    @Email
    private String email;

    @ApiModelProperty(example = "false")
    private boolean ativado;

    private List<GrupoModel> grupos = new ArrayList<>();

    @ApiModelProperty(example = "99999999987")
    private String cpf;

    @ApiModelProperty(example = "M")
    private String sexo;

    @ApiModelProperty(example = "91981551703")
    private String telefone;

    @ApiModelProperty(example = "91981551703")
    private String celular;

    @ApiModelProperty(example = "31/05/1988")
    private OffsetDateTime dtNascimento;

    @ApiModelProperty(example = "04010200")
    private String cep;

    @ApiModelProperty(example = "Rua Domingos de Morais")
    private String logradouro;

    @ApiModelProperty(example = "1368")
    private String numero;

    @ApiModelProperty(example = "Complemento")
    private String complemento;

    @ApiModelProperty(example = "Vila Mariana")
    private String bairro;

    private CidadeModel cidade;

    @ApiModelProperty(example = "Fulana de Tal")
    private String nomeMae;

    @ApiModelProperty(example = "Fulano de Tal")
    private String nomePai;

    @ApiModelProperty(example = "99999999987")
    private String cpfResponsavel;

    private ProfissionalModel profissional;

    private Long clinicaId;
}
