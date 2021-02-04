package br.com.siberius.projeto.domain.repository;

import br.com.siberius.projeto.domain.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PacienteRepository extends JpaRepository<Paciente, Long>, JpaSpecificationExecutor<Paciente> {

    Optional<Paciente> findByEmail(String email);

    @Query("SELECT count(p.id) FROM Paciente p where p.ativado = true and p.profissionalId = :profissionalId")
    long countPacienteByAtivado( @Param("profissionalId") Long profissionalId);

    @Query("SELECT count(p.id) FROM Paciente p where p.ativado = true and p.clinicaId = :clinicaId")
    long countPacienteByAtivadoClinica( @Param("clinicaId") Long clinicaId);
}
