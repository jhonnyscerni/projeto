package br.com.siberius.projeto.api.model.input;

import br.com.siberius.projeto.api.model.CidadeModel;
import br.com.siberius.projeto.api.model.GrupoModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.OffsetDateTime;
import java.util.Date;
import java.util.List;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClinicaInputModel {

    @ApiModelProperty(example = "1")
    private Long id;

    @NotBlank
    @ApiModelProperty(example = "Jhonny Scerni")
    private String nome;

    @NotBlank
    @Email
    @ApiModelProperty(example = "jhonnyscerni@gmail.com")
    private String email;

    @ApiModelProperty(example = "2020-10-23T18:16:59.517Z")
    private OffsetDateTime dataCadastro;

    private List<GrupoModel> grupos;

    @ApiModelProperty(example = "NAO")
    private String ativado;

    @ApiModelProperty(example = "99999999987")
    private String cpf;

    @ApiModelProperty(example = "M")
    private String sexo;

    @ApiModelProperty(example = "91981551703")
    private String telefone;

    @ApiModelProperty(example = "91981551703")
    private String celular;

    @ApiModelProperty(example = "31/05/1988")
    private Date dtNascimento;

    @ApiModelProperty(example = "04010200")
    private String cep;

    @ApiModelProperty(example = "Rua Domingos de Morais")
    private String logradouro;

    @ApiModelProperty(example = "1368")
    private String numero;

    @ApiModelProperty(example = "COmplemento")
    private String complemento;

    @ApiModelProperty(example = "Vila Mariana")
    private String bairro;

    private CidadeModel cidade;

    @ApiModelProperty(example = "3")
    private Long clinicaId;
}
