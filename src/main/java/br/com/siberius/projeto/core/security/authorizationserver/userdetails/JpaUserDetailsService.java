package br.com.siberius.projeto.core.security.authorizationserver.userdetails;

import br.com.siberius.projeto.api.assembler.GrupoModelAssembler;
import br.com.siberius.projeto.domain.model.Usuario;
import br.com.siberius.projeto.domain.repository.UsuarioRepository;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.DiscriminatorValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// Classe onde o Spring vai usar para consultar nosso usuario no banco de dados pelo metodo "loadUserByUsername" do UserDetailsService
@Service
public class JpaUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    GrupoModelAssembler assembler;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(username)
            .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com e-mail informado"));

        if (!usuario.isAtivado()){
            throw new UsernameNotFoundException("Usuário cadastrado mas ainda não está Ativado");
        }

        return new AuthUser(usuario, getAuthorities(usuario));
    }

    private Collection<GrantedAuthority> getAuthorities(Usuario usuario) {
        return usuario.getGrupos().stream()
            .flatMap(grupo -> grupo.getPermissoes().stream())
            .map(permissao -> new SimpleGrantedAuthority(permissao.getNome().toUpperCase()))
            .collect(Collectors.toSet());
    }

    private List<GrupoModelCustomClaims> getGrupos(Usuario usuario) {
        return usuario.getGrupos().stream().map(grupo -> assembler.toModelCustomClaims(grupo)).collect(Collectors.toList());
    }
}