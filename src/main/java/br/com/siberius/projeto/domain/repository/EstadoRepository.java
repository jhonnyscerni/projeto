package br.com.siberius.projeto.domain.repository;

import br.com.siberius.projeto.domain.model.Estado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoRepository extends JpaRepository<Estado, Long> {

    Estado findBySigla(String siglaEstado);
}
