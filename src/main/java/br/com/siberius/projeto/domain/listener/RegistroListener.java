package br.com.siberius.projeto.domain.listener;

import br.com.siberius.projeto.api.event.RegistroCompletoEvent;
import br.com.siberius.projeto.domain.model.Usuario;
import br.com.siberius.projeto.domain.service.EnvioEmailService;
import br.com.siberius.projeto.domain.service.EnvioEmailService.Mensagem;
import br.com.siberius.projeto.domain.service.UsuarioService;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class RegistroListener implements
    ApplicationListener<RegistroCompletoEvent> {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private EnvioEmailService envioEmailService;

    @Override
    public void onApplicationEvent(RegistroCompletoEvent event) {
        this.confirmRegistration(event);
    }

    private void confirmRegistration(RegistroCompletoEvent event) {
        Usuario usuario = event.getUsuario();
        String token = UUID.randomUUID().toString();
        usuarioService.criarVerificaoToken(usuario, token);

        String confirmationUrl
            = "/registroConfirmado?token=" + token;

        Mensagem mensagem = Mensagem.builder()
            .assunto(usuario.getNome() + " - Ativação de conta")
            .corpo("modelo-confirmar-cadastro.html")
            .variavel("url_confirmation", "http://localhost:8080" + confirmationUrl)
            .variavel("usuario", usuario)
            .destinatario(usuario.getEmail())
            .build();
        envioEmailService.enviar(mensagem);
    }
}