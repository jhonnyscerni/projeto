package br.com.siberius.projeto.api.model.input;

import br.com.siberius.projeto.api.model.GrupoModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioInputModel {

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

    @ApiModelProperty(example = "Administrador", required = true)
    private List<GrupoModel> grupos = new ArrayList<>();
}
