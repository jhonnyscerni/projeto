package br.com.siberius.projeto.api.controller;

import br.com.siberius.projeto.api.assembler.UsuarioModelAssembler;
import br.com.siberius.projeto.api.assembler.disassembler.UsuarioInputModelDisassembler;
import br.com.siberius.projeto.api.event.RecuperarSenhaEvent;
import br.com.siberius.projeto.api.model.UsuarioModel;
import br.com.siberius.projeto.api.model.input.UsuarioInputRecuperarSenhaModel;
import br.com.siberius.projeto.domain.model.Usuario;
import br.com.siberius.projeto.domain.service.UsuarioService;
import java.util.UUID;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/recuperar-senha")
public class UsuarioRecuperarSenhaController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioModelAssembler assembler;

    @Autowired
    private UsuarioInputModelDisassembler disassembler;

    @Autowired
    ApplicationEventPublisher eventPublisher;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioModel adicionar(@RequestBody @Valid UsuarioInputRecuperarSenhaModel usuarioInput) {

        Usuario usuario = usuarioService.buscarOuFalharPorEmail(usuarioInput.getEmail());

        //Nova Senha
        String novaSenha = UUID.randomUUID().toString();

        String senhaCriptografada = new BCryptPasswordEncoder().encode(novaSenha);
        usuario.setSenha(senhaCriptografada);

        usuario = usuarioService.salvar(usuario);

        eventPublisher.publishEvent(new RecuperarSenhaEvent
            (usuario, novaSenha));

        return assembler.toModel(usuario);
    }

}
