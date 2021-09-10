package br.com.siberius.projeto.domain.repository;

import br.com.siberius.projeto.domain.model.Atendimento;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AtendimentoRepository extends JpaRepository<Atendimento, Long>, JpaSpecificationExecutor<Atendimento> {

    @Query("from Atendimento where consulta.id = :consulta and id =:atendimento")
    Optional<Atendimento> findById(@Param("consulta") Long consultaId,
        @Param("atendimento") Long atendimentoId);

    @Query("from Atendimento where consulta.id = :consulta")
    Optional<Atendimento> findConsultaId(@Param("consulta") Long consultaId);

}
