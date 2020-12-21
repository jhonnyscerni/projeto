package br.com.siberius.projeto.api.model.input;

import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioInputRecuperarSenhaModel {

    @ApiModelProperty(example = "jhonnyscerni@gmail.com", required = true)
    @NotBlank
    @Email
    private String email;
}
