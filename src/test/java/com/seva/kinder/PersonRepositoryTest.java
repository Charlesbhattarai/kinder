package com.seva.kinder;


import com.seva.kinder.entity.Person;
import com.seva.kinder.repository.PersonRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.JsonPathAssertions;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;


@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@DataJpaTest
public class PersonRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired
    private PersonRepository personRepository;

    @Test
    public void whenFindAll() {
        //given
        Person person = new Person();
        person.setFullName("Arun");
        testEntityManager.persist(person);
        testEntityManager.flush();

        Person person1 = new Person();
        person1.setFullName("anil");
        testEntityManager.persist(person1);
        testEntityManager.flush();

        //when
        List<Person> personList = personRepository.findAll();
    }
}