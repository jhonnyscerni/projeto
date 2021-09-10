package br.com.siberius.projeto.domain.service;

import br.com.siberius.projeto.domain.exception.EntidadeEmUsoException;
import br.com.siberius.projeto.domain.exception.NegocioException;
import br.com.siberius.projeto.domain.exception.model.UsuarioNaoEncontradoException;
import br.com.siberius.projeto.domain.model.Grupo;
import br.com.siberius.projeto.domain.model.Profissional;
import br.com.siberius.projeto.domain.repository.ProfissionalRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ProfissionalService {

    @Autowired
    private ProfissionalRepository profissionalRepository;

    @Autowired
    GrupoService grupoService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final String MSG_USUARIO_EM_USO
        = "Usuário de código %d não pode ser removida, pois está em uso";

    public Profissional buscarOuFalhar(Long profissionalId) {
        return profissionalRepository.findById(profissionalId)
            .orElseThrow(() -> new UsuarioNaoEncontradoException(profissionalId));
    }

    public Profissional salvar(Profissional profissional) {
        Optional<Profissional> profissionalExistente = profissionalRepository.findByEmail(profissional.getEmail());

        if (profissionalExistente.isPresent() && !profissionalExistente.get().equals(profissional)) {
            throw new NegocioException(
                String.format("Já existe um usuário cadastrado com o e-mail %s", profissional.getEmail()));
        }

        if (profissional.isNovo()) {
            profissional.setSenha(passwordEncoder.encode(profissional.getSenha()));
        }

        if (profissional.isNovo()) {
            List<Grupo> grupos = new ArrayList<>();
            for (Grupo grupo : profissional.getGrupos()) {
                grupos.add(grupoService.buscarOuFalhar(grupo.getId()));
            }
            profissional.setGrupos(grupos);
        }

        return profissionalRepository.save(profissional);
    }

    public void excluir(Long profissionalId) {
        try {
            profissionalRepository.deleteById(profissionalId);
        } catch (EmptyResultDataAccessException e) {
            throw new UsuarioNaoEncontradoException(profissionalId);

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                String.format(MSG_USUARIO_EM_USO, profissionalId));
        }
    }

    public void alterarSenha(Long profissionalId, String senhaAtual, String novaSenha) {
        Profissional profissional = this.buscarOuFalhar(profissionalId);
//        if (usuario.senhaNaoCoincideCom(senhaAtual)) {
//            throw new NegocioException("Senha atual informada não coincide com a senha do usuário.");
//        }
        if (!passwordEncoder.matches(senhaAtual, profissional.getSenha())) {
            throw new NegocioException("Senha atual informada não coincide com a senha do usuário.");
        }
        profissional.setSenha(novaSenha);
        profissionalRepository.save(profissional);
    }

    public void associarGrupo(Long profissionalId, Long grupoId) {
        Profissional profissional = buscarOuFalhar(profissionalId);
        Grupo grupo = grupoService.buscarOuFalhar(grupoId);
        profissional.adicionarGrupo(grupo);
        profissionalRepository.save(profissional);
    }

    public void desassociarGrupo(Long profissionalId, Long grupoId) {
        Profissional profissional = buscarOuFalhar(profissionalId);
        Grupo grupo = grupoService.buscarOuFalhar(grupoId);
        profissional.removerGrupo(grupo);
        profissionalRepository.save(profissional);
    }

}
