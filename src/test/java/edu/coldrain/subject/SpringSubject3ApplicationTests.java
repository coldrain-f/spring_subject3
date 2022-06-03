package edu.coldrain.subject;

import com.querydsl.jpa.impl.JPAQueryFactory;
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

    @Test
    void contextLoads() {

    }

}
