package br.com.siberius.projeto.domain.repository;

import br.com.siberius.projeto.domain.model.CategoriaLancamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaLancamentoRepository extends JpaRepository<CategoriaLancamento, Long> {

}