package br.com.siberius.projeto.domain.repository;

import br.com.siberius.projeto.domain.model.Profissional;
import br.com.siberius.projeto.domain.model.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>, JpaSpecificationExecutor<Usuario> {

    @Query("select u from Usuario u join fetch u.cidade ci join fetch ci.estado join fetch u.grupos gu join fetch gu.permissoes per where u.email =:email")
    Optional<Usuario> findByEmail(@Param("email") String email);

    @Query("select u from Usuario u join fetch u.cidade ci join fetch ci.estado join fetch u.grupos gu join fetch gu.permissoes per where u.id =:id")
    Optional<Usuario>findById(@Param("id") Long id);
}
