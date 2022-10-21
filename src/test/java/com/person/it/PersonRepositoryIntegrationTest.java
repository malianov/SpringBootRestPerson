package com.person.it;

import com.person.model.Person;
import com.person.repository.PersonRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Integration test for Repository")
@ExtendWith(SpringExtension.class)
@DataJpaTest
public class PersonRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PersonRepository repository;

    @AfterEach
    public void resetDb() {
        repository.deleteAll();
    }

    @DisplayName("Get person when send http's GET request by ID successfully")
    @Test
    public void getPersonDTO_whenGetPerson() {

        // prepare
        String name = "Igor";
        String surname = "Malianov";

        LocalDate birthDay = LocalDate.of(1978, 05, 22);

        Person person = new Person(name, surname, birthDay);
        entityManager.persistAndFlush(person);

        // when
        Person fromDb = repository.findById(person.getId()).orElse(null);

        // then
        assertThat(fromDb.getName()).isEqualTo(person.getName());
    }

    @DisplayName("Get null when send http's GET request by ID with invalid ID")
    @Test
    public void whenInvalidId_thenReturnNull() {

        // when
        Person fromDb = repository.findById(-11l).orElse(null);

        // then
        assertThat(fromDb).isNull();
    }
}
