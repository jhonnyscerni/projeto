package br.com.siberius.projeto.domain.repository;

import br.com.siberius.projeto.domain.model.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long>, JpaSpecificationExecutor<Consulta> {


    @Query("SELECT count(c.id) FROM Consulta c where c.statusConsultaEnum = 'FINALIZADO' and c.profissional.id = :profissionalId")
    long countConsultaByStatusConsultaEnumFinalizado(@Param("profissionalId") Long profissionalId);

    @Query("SELECT count(c.id) FROM Consulta c where c.statusConsultaEnum = 'CONFIRMADO' and c.profissional.id = :profissionalId")
    long countConsultaByStatusConsultaEnumConfirmado(@Param("profissionalId") Long profissionalId);

    @Query("SELECT count(c.id) FROM Consulta c where c.statusConsultaEnum = 'AGENDADO' and c.profissional.id = :profissionalId")
    long countConsultaByStatusConsultaEnumAgendado(@Param("profissionalId") Long profissionalId);

}
