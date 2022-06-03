package edu.coldrain.subject;

import com.querydsl.jpa.impl.JPAQueryFactory;
import edu.coldrain.subject.entity.Hello;
import edu.coldrain.subject.entity.QHello;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

import static edu.coldrain.subject.entity.QHello.hello;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class SpringSubject3ApplicationTests {

    @PersistenceContext
    private EntityManager em;

    private JPAQueryFactory query;

    @BeforeEach
    void before() {
        query = new JPAQueryFactory(em);
    }

    @Test
    void contextLoads() {
        Hello h = new Hello();
        em.persist(h);

        List<Hello> fetch = query.selectFrom(hello)
                .fetch();

        assertThat(fetch).hasSize(1);
    }

}
