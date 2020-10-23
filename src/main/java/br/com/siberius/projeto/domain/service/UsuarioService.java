package br.com.siberius.projeto.domain.service;

import br.com.siberius.projeto.domain.exception.model.UsuarioNaoEncontradoException;
import br.com.siberius.projeto.domain.model.Usuario;
import br.com.siberius.projeto.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario buscarOuFalhar(Long usuarioId) {
        return usuarioRepository.findById(usuarioId)
            .orElseThrow(() -> new UsuarioNaoEncontradoException(usuarioId));
    }
}
