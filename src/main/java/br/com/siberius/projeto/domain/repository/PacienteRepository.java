package br.com.siberius.projeto.domain.repository;

import br.com.siberius.projeto.domain.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PacienteRepository extends JpaRepository<Paciente, Long>, JpaSpecificationExecutor<Paciente> {

    @Query("select u from Paciente u join fetch u.cidade ci join fetch ci.estado join fetch u.grupos gu join fetch gu.permissoes per where u.email =:email")
    Optional<Paciente> findByEmail(@Param("email") String email);

    @Query("select u from Paciente u join fetch u.cidade ci join fetch ci.estado join fetch u.grupos gu join fetch gu.permissoes per where u.id =:pacienteId")
    Optional<Paciente>findById(@Param("pacienteId") Long pacienteId);

    @Query("SELECT count(p.id) FROM Paciente p where p.ativado = 'SIM' and p.profissionalId = :profissionalId")
    long countPacienteByAtivado(@Param("profissionalId") Long profissionalId);

    @Query("SELECT count(p.id) FROM Paciente p where p.ativado = 'SIM' and p.clinicaId = :clinicaId")
    long countPacienteByAtivadoClinica(@Param("clinicaId") Long clinicaId);
}
