package br.com.siberius.projeto.domain.repository;

import br.com.siberius.projeto.domain.model.Usuario;
import br.com.siberius.projeto.domain.model.VerificarToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificarTokenRepository
    extends JpaRepository<VerificarToken, Long> {

    VerificarToken findByToken(String token);

    VerificarToken findByUsuario(Usuario usuario);
}
