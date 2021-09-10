package br.com.siberius.projeto.domain.listener;

import br.com.siberius.projeto.api.event.RecuperarSenhaEvent;
import br.com.siberius.projeto.domain.model.Usuario;
import br.com.siberius.projeto.domain.service.EnvioEmailService;
import br.com.siberius.projeto.domain.service.EnvioEmailService.Mensagem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class RecuperarSenhaListener implements
    ApplicationListener<RecuperarSenhaEvent> {

    @Autowired
    private EnvioEmailService envioEmailService;

    @Override
    public void onApplicationEvent(RecuperarSenhaEvent event) {
        this.recuperarSenha(event);
    }

    private void recuperarSenha(RecuperarSenhaEvent event) {
        Usuario usuario = event.getUsuario();
        String novaSenha = event.getNovaSenha();

        Mensagem mensagem = Mensagem.builder()
            .assunto(usuario.getNome() + " - Recuperação de senha")
            .corpo("modelo-recuperar-senha.html")
            .variavel("usuario", usuario)
            .variavel("novaSenha", novaSenha)
            .destinatario(usuario.getEmail())
            .build();
        envioEmailService.enviar(mensagem);
    }
}