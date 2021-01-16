package br.com.siberius.projeto.domain.service;

import br.com.siberius.projeto.core.security.resourceserver.ProjetoSecurity;
import br.com.siberius.projeto.domain.exception.EntidadeEmUsoException;
import br.com.siberius.projeto.domain.exception.NegocioException;
import br.com.siberius.projeto.domain.exception.model.UsuarioNaoEncontradoException;
import br.com.siberius.projeto.domain.model.Grupo;
import br.com.siberius.projeto.domain.model.Paciente;
import br.com.siberius.projeto.domain.model.Profissional;
import br.com.siberius.projeto.domain.model.Usuario;
import br.com.siberius.projeto.domain.repository.PacienteRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    GrupoService grupoService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ProfissionalService profissionalService;

    @Autowired
    private ProjetoSecurity projetoSecurity;

    @Autowired
    private UsuarioService usuarioService;

    private static final String MSG_USUARIO_EM_USO
        = "Usuário de código %d não pode ser removida, pois está em uso";

    public Paciente buscarOuFalhar(Long pacienteId) {
        return pacienteRepository.findById(pacienteId)
            .orElseThrow(() -> new UsuarioNaoEncontradoException(pacienteId));
    }

    public Paciente salvar(Paciente paciente) {
        Optional<Paciente> optionalPaciente = pacienteRepository.findByEmail(paciente.getEmail());
//        paciente.setProfissional(new Profissional());
//        paciente.getProfissional().setId(projetoSecurity.getUsuarioId());
        Usuario usuario = usuarioService.buscarOuFalhar(projetoSecurity.getUsuarioId());
        if (usuario.getDiscriminatorValue().equals("User")) {
            paciente.setProfissionalId(projetoSecurity.getUsuarioId());
        }
        else if(usuario.getDiscriminatorValue().equals("Clinic")){
            paciente.setClinicaId(projetoSecurity.getUsuarioId());
        }

        if (optionalPaciente.isPresent() && !optionalPaciente.get().equals(paciente)) {
            throw new NegocioException(
                String.format("Já existe um paciente cadastrado com o e-mail %s", paciente.getEmail()));
        }

        if (paciente.isNovo()) {
            paciente.setSenha(passwordEncoder.encode(paciente.getSenha()));
        }

        if (paciente.isNovo()) {
            List<Grupo> grupos = new ArrayList<>();
            for (Grupo grupo : paciente.getGrupos()) {
                grupos.add(grupoService.buscarOuFalhar(grupo.getId()));
            }
            paciente.setGrupos(grupos);
        }

        return pacienteRepository.save(paciente);
    }

    public void excluir(Long pacienteId) {
        try {
            pacienteRepository.deleteById(pacienteId);
        } catch (EmptyResultDataAccessException e) {
            throw new UsuarioNaoEncontradoException(pacienteId);

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                String.format(MSG_USUARIO_EM_USO, pacienteId));
        }
    }

    public void alterarSenha(Long pacienteId, String senhaAtual, String novaSenha) {
        Paciente paciente = this.buscarOuFalhar(pacienteId);
//        if (usuario.senhaNaoCoincideCom(senhaAtual)) {
//            throw new NegocioException("Senha atual informada não coincide com a senha do usuário.");
//        }
        if (!passwordEncoder.matches(senhaAtual, paciente.getSenha())) {
            throw new NegocioException("Senha atual informada não coincide com a senha do usuário.");
        }
        paciente.setSenha(novaSenha);
        pacienteRepository.save(paciente);
    }

    public void associarGrupo(Long pacienteId, Long grupoId) {
        Paciente paciente = buscarOuFalhar(pacienteId);
        Grupo grupo = grupoService.buscarOuFalhar(grupoId);
        paciente.adicionarGrupo(grupo);
        pacienteRepository.save(paciente);
    }

    public void desassociarGrupo(Long pacienteId, Long grupoId) {
        Paciente paciente = buscarOuFalhar(pacienteId);
        Grupo grupo = grupoService.buscarOuFalhar(grupoId);
        paciente.removerGrupo(grupo);
        pacienteRepository.save(paciente);
    }

}
