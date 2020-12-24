package br.com.siberius.projeto.core.security.authorizationserver.userdetails;

import br.com.siberius.projeto.domain.model.Usuario;
import java.util.Collection;
import java.util.List;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

// Como a classe User não tem algumas propriedades criamos o AuthUser para adicionarmos novas propriedades ex: fullName
@Getter
public class AuthUser extends User {

    private static final long serialVersionUID = 1L;

    private String fullName;
    private Long userId;
    private List<GrupoModelCustomClaims> grupos;

    public AuthUser(Usuario usuario, Collection<? extends GrantedAuthority> authorities, List<GrupoModelCustomClaims> grupos) {
        super(usuario.getEmail(), usuario.getSenha(), authorities);

        this.userId = usuario.getId();
        this.fullName = usuario.getNome();
        this.grupos = grupos;
    }

}