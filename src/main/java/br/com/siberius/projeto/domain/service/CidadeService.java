package br.com.siberius.projeto.domain.service;

import br.com.siberius.projeto.domain.exception.model.CidadeNaoEncontradoException;
import br.com.siberius.projeto.domain.model.Cidade;
import br.com.siberius.projeto.domain.model.Estado;
import br.com.siberius.projeto.domain.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CidadeService {

    private static final String MSG_CIDADE_EM_USO
            = "Cidade de código %d não pode ser removida, pois está em uso";

    @Autowired
    private CidadeRepository cidadeRepository;

    public Cidade buscarOuFalhar(Long cidadeId) {
        return cidadeRepository.findById(cidadeId).orElseThrow(() -> new CidadeNaoEncontradoException(cidadeId));
    }

    public Cidade buscarPorNomeESiglaEstado(String nomeCidade, String sigla) {
        return cidadeRepository.findByNomeAndEstado_Sigla(nomeCidade, sigla).orElseThrow(() -> new CidadeNaoEncontradoException("Cidade nao encontrada pelo Nome da cidade ou Id do Estado"));
    }

}
