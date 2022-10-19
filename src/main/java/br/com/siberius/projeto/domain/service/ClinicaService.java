package br.com.siberius.projeto.domain.service;

import br.com.siberius.projeto.domain.exception.EntidadeEmUsoException;
import br.com.siberius.projeto.domain.exception.NegocioException;
import br.com.siberius.projeto.domain.exception.model.CidadeNaoEncontradoException;
import br.com.siberius.projeto.domain.exception.model.ClinicaNaoEncontradoException;
import br.com.siberius.projeto.domain.exception.model.UsuarioNaoEncontradoException;
import br.com.siberius.projeto.domain.model.Cidade;
import br.com.siberius.projeto.domain.model.Clinica;
import br.com.siberius.projeto.domain.model.Grupo;
import br.com.siberius.projeto.domain.model.Profissional;
import br.com.siberius.projeto.domain.repository.ClinicaRepository;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ClinicaService {

    private static final String MSG_CLINICA_EM_USO
        = "Clinica de código %d não pode ser removida, pois está em uso";

    @Autowired
    private ClinicaRepository clinicaRepository;

    @Autowired
    GrupoService grupoService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Clinica buscarOuFalhar(Long clinicaId) {
        return clinicaRepository.findById(clinicaId).orElseThrow(() -> new ClinicaNaoEncontradoException(clinicaId));
    }

    public Clinica salvar(Clinica clinica) {
        Optional<Clinica> clinicaExistente = clinicaRepository.findByEmail(clinica.getEmail());

        if (clinicaExistente.isPresent() && !clinicaExistente.get().equals(clinica)) {
            throw new NegocioException(
                String.format("Já existe um usuário cadastrado com o e-mail %s", clinica.getEmail()));
        }

        if (clinica.isNovo()) {
            clinica.setSenha(passwordEncoder.encode(clinica.getSenha()));
        } else if (!(clinica.getSenha().equals(clinicaExistente.get().getSenha()))) {
            clinica.setSenha(passwordEncoder.encode(clinica.getSenha()));
        }

        if (clinica.isNovo()) {
            Set<Grupo> grupos = new HashSet<>();
            for (Grupo grupo : clinica.getGrupos()) {
                grupos.add(grupoService.buscarOuFalhar(grupo.getId()));
            }
            clinica.setGrupos(grupos);
        }

        return clinicaRepository.save(clinica);
    }

    public void excluir(Long clinicaId) {
        try {
            clinicaRepository.deleteById(clinicaId);
        } catch (EmptyResultDataAccessException e) {
            throw new ClinicaNaoEncontradoException(clinicaId);

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                String.format(MSG_CLINICA_EM_USO, clinicaId));
        }
    }

    public void alterarSenha(Long clinicaId, String senhaAtual, String novaSenha) {
        Clinica clinica = this.buscarOuFalhar(clinicaId);
//        if (usuario.senhaNaoCoincideCom(senhaAtual)) {
//            throw new NegocioException("Senha atual informada não coincide com a senha do usuário.");
//        }
        if (!passwordEncoder.matches(senhaAtual, clinica.getSenha())) {
            throw new NegocioException("Senha atual informada não coincide com a senha do usuário.");
        }
        clinica.setSenha(novaSenha);
        clinicaRepository.save(clinica);
    }

    public void associarGrupo(Long clinicaId, Long grupoId) {
        Clinica clinica = buscarOuFalhar(clinicaId);
        Grupo grupo = grupoService.buscarOuFalhar(grupoId);
        clinica.adicionarGrupo(grupo);
        clinicaRepository.save(clinica);
    }

    public void desassociarGrupo(Long clinicaId, Long grupoId) {
        Clinica clinica = buscarOuFalhar(clinicaId);
        Grupo grupo = grupoService.buscarOuFalhar(grupoId);
        clinica.removerGrupo(grupo);
        clinicaRepository.save(clinica);
    }

}
