package com.wagnerrdemorais.forum.repository;

import com.wagnerrdemorais.forum.modelo.Curso;
import io.jsonwebtoken.lang.Assert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // nao substituir o banco pelo h2
@ActiveProfiles("test")
public class CursoRepositoryTest {

    @Autowired
    private CursoRepository repository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void deveriaCarregarCursoAoBuscarPeloNome() {
        String nomeCurso = "HTML 5";

        Curso curso = new Curso();
        curso.setNome(nomeCurso);
        curso.setCategoria("Programacao");

        testEntityManager.persist(curso);

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