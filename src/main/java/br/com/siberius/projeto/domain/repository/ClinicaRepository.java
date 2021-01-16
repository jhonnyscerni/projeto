package br.com.siberius.projeto.domain.repository;

import br.com.siberius.projeto.domain.model.Clinica;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ClinicaRepository extends JpaRepository<Clinica, Long>, JpaSpecificationExecutor<Clinica> {

    Optional<Clinica> findByEmail(String email);
}
