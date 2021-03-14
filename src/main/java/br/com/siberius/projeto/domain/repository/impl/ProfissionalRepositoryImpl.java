package br.com.siberius.projeto.domain.repository.impl;

import br.com.siberius.projeto.domain.model.FotoPerfil;
import br.com.siberius.projeto.domain.repository.querys.ProfissionalRepositoryQueries;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
public class ProfissionalRepositoryImpl implements ProfissionalRepositoryQueries {

    @PersistenceContext
    private EntityManager manager;

    @Transactional
    @Override
    public FotoPerfil save(FotoPerfil foto) {
        return manager.merge(foto);
    }

    @Override
    public void delete(FotoPerfil foto) {
        manager.remove(foto);
    }
}
