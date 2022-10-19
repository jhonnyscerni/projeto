package br.com.siberius.projeto.domain.repository;

import br.com.siberius.projeto.domain.model.Grupo;
import br.com.siberius.projeto.domain.model.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GrupoRepository extends JpaRepository<Grupo, Long> {

    @Query("select u from Grupo u join fetch u.permissoes per where u.id =:id")
    Optional<Grupo> findById(@Param("id") Long id);
}
