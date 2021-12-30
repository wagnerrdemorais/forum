package com.wagnerrdemorais.forum.repository;

import com.wagnerrdemorais.forum.modelo.Curso;
import io.jsonwebtoken.lang.Assert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class CursoRepositoryTest {

    @Autowired
    private CursoRepository repository;

    @Test
    public void deveriaCarregarCursoAoBuscarPeloNome() {
        String nomeCurso = "HTML 5";
        Curso byNome = repository.findByNome(nomeCurso);

        Assert.notNull(byNome);
        Assertions.assertThat(byNome.getNome()).isEqualTo(nomeCurso);
    }

    @Test
    public void naoDeveriaCarregarCursoAoBuscarPeloNomeCasoNaoExista() {
        String nomeCurso = "HTML 2000";
        Curso byNome = repository.findByNome(nomeCurso);

        Assert.isNull(byNome);
    }
}