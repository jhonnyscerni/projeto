package br.com.siberius.projeto.api.event;

import br.com.siberius.projeto.domain.model.Usuario;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class RecuperarSenhaEvent extends ApplicationEvent {

    private Usuario usuario;
    private String novaSenha;

    public RecuperarSenhaEvent(Usuario usuario, String novaSenha) {
        super(usuario);

        this.usuario = usuario;
        this.novaSenha = novaSenha;
    }
}