package br.com.siberius.projeto.api.controller;

import br.com.siberius.projeto.api.openapi.controller.UsuarioValidarTokenControllerOpenApi;
import br.com.siberius.projeto.domain.model.Usuario;
import br.com.siberius.projeto.domain.model.VerificarToken;
import br.com.siberius.projeto.domain.service.UsuarioService;
import java.util.Calendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path = "/registro-confirmado")
public class UsuarioValidarTokenController implements UsuarioValidarTokenControllerOpenApi {

    @Autowired
    private UsuarioService usuarioService;

    @Override
    @GetMapping
    public ModelAndView confirmRegistration(@RequestParam("token") String token) {

        VerificarToken verificarToken = usuarioService.getVerificarToken(token);

        if (verificarToken == null) {
            return new ModelAndView("pages/bad-user");
        }

        Usuario usuario = verificarToken.getUsuario();
        Calendar cal = Calendar.getInstance();
        if ((verificarToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            return new ModelAndView("pages/bad-user");
        }

        usuario.setAtivado(true);

        usuarioService.salvar(usuario);
        return new ModelAndView("modelo-confimarcao-sucesso");
    }
}
