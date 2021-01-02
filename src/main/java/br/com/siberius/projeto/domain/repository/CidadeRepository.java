package br.com.siberius.projeto.domain.repository;

import br.com.siberius.projeto.domain.model.Cidade;
import br.com.siberius.projeto.domain.model.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface CidadeRepository extends JpaRepository<Cidade, Long>, JpaSpecificationExecutor<Cidade> {

    List<Cidade> findByEstado_Id(Long estadoId);

    Optional<Cidade> findByNomeAndEstado_Sigla(String nomeCidade, String sigla);
}
