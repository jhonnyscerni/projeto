package br.com.siberius.projeto.domain.repository;

import br.com.siberius.projeto.domain.model.Profissional;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProfissionalRepository extends JpaRepository<Profissional, Long>, JpaSpecificationExecutor<Profissional> {

    Optional<Profissional> findByEmail(String email);
}
