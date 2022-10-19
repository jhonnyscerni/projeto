package br.com.siberius.projeto.domain.repository;

import br.com.siberius.projeto.domain.model.Lancamento;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LancamentoRepository extends JpaRepository<Lancamento, Long>, JpaSpecificationExecutor<Lancamento> {

    @Query("from Lancamento lc join fetch lc.consulta c where c.id = :consulta ")
    Lancamento findByConsultaId(@Param("consulta") Long consultaId);
}
