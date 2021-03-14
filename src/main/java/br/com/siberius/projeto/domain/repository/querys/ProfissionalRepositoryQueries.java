package br.com.siberius.projeto.domain.repository.querys;

import br.com.siberius.projeto.domain.model.FotoPerfil;

public interface ProfissionalRepositoryQueries {

    FotoPerfil save(FotoPerfil foto);

    void delete(FotoPerfil foto);
}
