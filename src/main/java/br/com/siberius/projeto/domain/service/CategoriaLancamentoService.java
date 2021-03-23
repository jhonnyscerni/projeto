package br.com.siberius.projeto.domain.service;

import br.com.siberius.projeto.domain.exception.EntidadeNaoEncontradaException;
import br.com.siberius.projeto.domain.exception.model.FormaPagamentoNaoEncontradoException;
import br.com.siberius.projeto.domain.model.CategoriaLancamento;
import br.com.siberius.projeto.domain.repository.CategoriaLancamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaLancamentoService {

    @Autowired
    private CategoriaLancamentoRepository categoriaLancamentoRepository;

    public CategoriaLancamento buscarOuFalhar(Long categoriaLancamentoId) {
        return categoriaLancamentoRepository.findById(categoriaLancamentoId)
            .orElseThrow(() -> new EntidadeNaoEncontradaException("Entidade Categoria Lançamento Não Encontrada"));
    }

}