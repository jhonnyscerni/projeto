package br.com.siberius.projeto.domain.service;

import br.com.siberius.projeto.domain.exception.EntidadeEmUsoException;
import br.com.siberius.projeto.domain.exception.NegocioException;
import br.com.siberius.projeto.domain.exception.model.UsuarioNaoEncontradoException;
import br.com.siberius.projeto.domain.model.Grupo;
import br.com.siberius.projeto.domain.model.Usuario;
import br.com.siberius.projeto.domain.model.VerificarToken;
import br.com.siberius.projeto.domain.repository.UsuarioRepository;
import br.com.siberius.projeto.domain.repository.VerificarTokenRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements IUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    GrupoService grupoService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private VerificarTokenRepository tokenRepository;

    private static final String MSG_USUARIO_EM_USO
        = "Usuário de código %d não pode ser removida, pois está em uso";

    public Usuario buscarOuFalhar(Long usuarioId) {
        return usuarioRepository.findById(usuarioId)
            .orElseThrow(() -> new UsuarioNaoEncontradoException(usuarioId));
    }

    public Usuario buscarOuFalharPorEmail(String email) {
        return usuarioRepository.findByEmail(email)
            .orElseThrow(() -> new UsuarioNaoEncontradoException(
                String.format("Não existe um cadastro de usuário com email %s", email)));
    }

    public Usuario salvar(Usuario usuario) {
        Optional<Usuario> usuarioExistente = usuarioRepository.findByEmail(usuario.getEmail());

        if (usuarioExistente.isPresent() && !usuarioExistente.get().equals(usuario)) {
            throw new NegocioException(
                String.format("Já existe um usuário cadastrado com o e-mail %s", usuario.getEmail()));
        }

        if (usuario.isNovo()) {
            usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        }

        if (usuario.isNovo()) {
            List<Grupo> grupos = new ArrayList<>();
            for (Grupo grupo : usuario.getGrupos()) {
                grupos.add(grupoService.buscarOuFalhar(grupo.getId()));
            }
            usuario.setGrupos(grupos);
        }

        return usuarioRepository.save(usuario);
    }

    public void excluir(Long usuarioId) {
        try {
            usuarioRepository.deleteById(usuarioId);
        } catch (EmptyResultDataAccessException e) {
            throw new UsuarioNaoEncontradoException(usuarioId);

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                String.format(MSG_USUARIO_EM_USO, usuarioId));
        }
    }

    public void alterarSenha(Long usuarioId, String senhaAtual, String novaSenha) {
        Usuario usuario = this.buscarOuFalhar(usuarioId);
//        if (usuario.senhaNaoCoincideCom(senhaAtual)) {
//            throw new NegocioException("Senha atual informada não coincide com a senha do usuário.");
//        }
        if (!passwordEncoder.matches(senhaAtual, usuario.getSenha())) {
            throw new NegocioException("Senha atual informada não coincide com a senha do usuário.");
        }
        usuario.setSenha(novaSenha);
        usuarioRepository.save(usuario);
    }

    public void associarGrupo(Long usuarioId, Long grupoId) {
        Usuario usuario = buscarOuFalhar(usuarioId);
        Grupo grupo = grupoService.buscarOuFalhar(grupoId);
        usuario.adicionarGrupo(grupo);
        usuarioRepository.save(usuario);
    }

    public void desassociarGrupo(Long usuarioId, Long grupoId) {
        Usuario usuario = buscarOuFalhar(usuarioId);
        Grupo grupo = grupoService.buscarOuFalhar(grupoId);
        usuario.removerGrupo(grupo);
        usuarioRepository.save(usuario);
    }

    @Override
    public Usuario getUsuario(String verificarToken) {
        Usuario usuario = tokenRepository.findByToken(verificarToken).getUsuario();
        return usuario;
    }

    @Override
    public void criarVerificaoToken(Usuario usuario, String token) {
        VerificarToken myToken = new VerificarToken(token, usuario);
        tokenRepository.save(myToken);
    }

    @Override
    public VerificarToken getVerificarToken(String verificarToken) {
        return tokenRepository.findByToken(verificarToken);
    }
}
