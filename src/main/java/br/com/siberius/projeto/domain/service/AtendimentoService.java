package br.com.siberius.projeto.domain.service;

import br.com.siberius.projeto.core.security.resourceserver.ProjetoSecurity;
import br.com.siberius.projeto.domain.exception.EntidadeEmUsoException;
import br.com.siberius.projeto.domain.exception.model.AtendimentoNaoEncontradoException;
import br.com.siberius.projeto.domain.exception.model.ConsultaNaoEncontradoException;
import br.com.siberius.projeto.domain.model.Atendimento;
import br.com.siberius.projeto.domain.model.Consulta;
import br.com.siberius.projeto.domain.model.Paciente;
import br.com.siberius.projeto.domain.model.Profissional;
import br.com.siberius.projeto.domain.model.Usuario;
import br.com.siberius.projeto.domain.repository.AtendimentoRepository;
import br.com.siberius.projeto.domain.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class AtendimentoService {

    private static final String MSG_ATENDIMENTO_EM_USO
        = "Atendimento de código %d não pode ser removida, pois está em uso";

    @Autowired
    private AtendimentoRepository atendimentoRepository;


    public Atendimento buscarOuFalhar(Long atendimentoId) {
        return atendimentoRepository.findById(atendimentoId).orElseThrow(() -> new AtendimentoNaoEncontradoException(atendimentoId));
    }

    public Atendimento buscarOuFalhar(Long consultaId, Long atendimentoId) {
        return atendimentoRepository.findById(consultaId, atendimentoId)
            .orElseThrow(() -> new AtendimentoNaoEncontradoException(consultaId, atendimentoId));
    }

    public Atendimento salvar(Atendimento atendimento) {
        return atendimentoRepository.save(atendimento);
    }

    public void excluir(Long atendimentoId) {
        try {
            atendimentoRepository.deleteById(atendimentoId);
            atendimentoRepository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new AtendimentoNaoEncontradoException(atendimentoId);
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                String.format(MSG_ATENDIMENTO_EM_USO, atendimentoId));
        }
    }

}
