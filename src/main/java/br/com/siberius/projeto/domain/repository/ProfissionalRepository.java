package br.com.siberius.projeto.domain.repository;

import br.com.siberius.projeto.domain.model.Paciente;
import br.com.siberius.projeto.domain.model.Profissional;
import br.com.siberius.projeto.domain.repository.querys.ProfissionalRepositoryQueries;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProfissionalRepository extends JpaRepository<Profissional, Long>, JpaSpecificationExecutor<Profissional>,
    ProfissionalRepositoryQueries {

    @Query("select u from Profissional u join fetch u.cidade ci join fetch ci.estado join fetch u.grupos gu join fetch gu.permissoes per where u.email =:email")
    Optional<Profissional> findByEmail(String email);

    @Query("select u from Profissional u join fetch u.cidade ci join fetch ci.estado join fetch u.grupos gu join fetch gu.permissoes per where u.id =:id")
    Optional<Profissional>findById(@Param("id") Long id);

    @Query("SELECT count(p.id) FROM Profissional p where p.ativado = 'SIM' and p.clinicaId = :clinicaId")
    long countProfissionaisByAtivadoClinica(@Param("clinicaId") Long clinicaId);
}
