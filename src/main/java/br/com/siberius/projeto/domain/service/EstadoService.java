package br.com.siberius.projeto.domain.service;

import br.com.siberius.projeto.domain.exception.model.EstadoNaoEncontradoException;
import br.com.siberius.projeto.domain.model.Estado;
import br.com.siberius.projeto.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstadoService {

    private static final String MSG_ESTADO_EM_USO
            = "Estado de código %d não pode ser removida, pois está em uso";

    @Autowired
    private EstadoRepository estadoRepository;

    public Estado buscarOuFalhar(Long estadoId) {
        return estadoRepository.findById(estadoId).orElseThrow(() -> new EstadoNaoEncontradoException(estadoId));
    }

}
