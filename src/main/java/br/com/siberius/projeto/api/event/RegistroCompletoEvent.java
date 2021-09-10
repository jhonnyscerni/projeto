package br.com.siberius.projeto.api.event;

import br.com.siberius.projeto.domain.model.Usuario;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class RegistroCompletoEvent extends ApplicationEvent {

    private Usuario usuario;

    public RegistroCompletoEvent(Usuario usuario) {
        super(usuario);

        this.usuario = usuario;
    }
}