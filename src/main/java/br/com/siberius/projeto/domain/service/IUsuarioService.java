package br.com.siberius.projeto.domain.service;

import br.com.siberius.projeto.domain.model.Usuario;
import br.com.siberius.projeto.domain.model.VerificarToken;

public interface IUsuarioService
{

    Usuario getUsuario(String verificarToken);

    void criarVerificaoToken(Usuario usuario, String token);

    VerificarToken getVerificarToken(String verificarToken);

}
