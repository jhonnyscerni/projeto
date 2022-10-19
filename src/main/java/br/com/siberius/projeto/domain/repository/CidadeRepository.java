package br.com.siberius.projeto.domain.repository;

import br.com.siberius.projeto.domain.model.Cidade;
import br.com.siberius.projeto.domain.model.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CidadeRepository extends JpaRepository<Cidade, Long>, JpaSpecificationExecutor<Cidade> {

    List<Cidade> findByEstado_Id(Long estadoId);

    @Query("select ci from Cidade ci join fetch ci.estado es where ci.nome =:nomeCidade and es.sigla=:sigla")
    Optional<Cidade> findByNomeAndEstado_Sigla(@Param("nomeCidade") String nomeCidade, @Param("sigla") String sigla);
}
