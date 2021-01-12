package br.com.siberius.projeto.domain.service;

import br.com.siberius.projeto.core.security.resourceserver.ProjetoSecurity;
import br.com.siberius.projeto.domain.exception.EntidadeEmUsoException;
import br.com.siberius.projeto.domain.exception.model.ConsultaNaoEncontradoException;
import br.com.siberius.projeto.domain.model.Consulta;
import br.com.siberius.projeto.domain.model.Paciente;
import br.com.siberius.projeto.domain.model.Profissional;
import br.com.siberius.projeto.domain.model.Usuario;
import br.com.siberius.projeto.domain.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class ConsultaService {

    private static final String MSG_CONSULTA_EM_USO
        = "Consulta de código %d não pode ser removida, pois está em uso";

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private ProfissionalService profissionalService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ProjetoSecurity projetoSecurity;


    public Consulta buscarOuFalhar(Long consultaId) {
        return consultaRepository.findById(consultaId).orElseThrow(() -> new ConsultaNaoEncontradoException(consultaId));
    }

    public Consulta salvar(Consulta consulta) {

        Usuario usuario = usuarioService.buscarOuFalhar(projetoSecurity.getUsuarioId());
        Profissional profissional = new Profissional();
        Paciente paciente = new Paciente();

        if (usuario.getDiscriminatorValue().equals("User")) {
            profissional = profissionalService.buscarOuFalhar(usuario.getId());
            paciente = pacienteService.buscarOuFalhar(consulta.getPaciente().getId());
            consulta.setTitle( " - Paciente: "+paciente.getNome());
        } else if (usuario.getDiscriminatorValue().equals("Patient")) {
            profissional = profissionalService.buscarOuFalhar(consulta.getProfissional().getId());
            paciente = pacienteService.buscarOuFalhar(usuario.getId());
            consulta.setTitle("Profissional: "+profissional.getNome() + " - Paciente: " + paciente.getNome());
        } else {
            profissional = profissionalService.buscarOuFalhar(consulta.getProfissional().getId());
            paciente = pacienteService.buscarOuFalhar(consulta.getPaciente().getId());
            consulta.setTitle("Profissional: "+profissional.getNome() + " - Paciente: " + paciente.getNome());
        }

        consulta.setStart(consulta.getDataHora());

        consulta.setProfissional(profissional);
        consulta.setPaciente(paciente);
        return consultaRepository.save(consulta);
    }

    public void excluir(Long consultaId) {
        try {
            consultaRepository.deleteById(consultaId);
            consultaRepository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new ConsultaNaoEncontradoException(consultaId);
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                String.format(MSG_CONSULTA_EM_USO, consultaId));
        }
    }

}
