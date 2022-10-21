package com.person.it;

import com.person.model.Person;
import com.person.model.PersonDTO;
import com.person.repository.PersonRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;
import java.util.Set;

@DisplayName("Integration test for Rest Controller")
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PersonControllerIntegrationTest {
    @Autowired
    private TestRestTemplate template;

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
        LocalDate today = LocalDate.now();

        int age = Period.between(birthDay, today).getYears();
        long id = createTestPerson(name, surname, birthDay).getId();

        // when
        PersonDTO personDTO = template.getForObject("/persons/{id}", PersonDTO.class, id);

        // then
        Assertions.assertEquals(personDTO.getName(), name);
        Assertions.assertEquals(personDTO.getSurname(), surname);
        Assertions.assertEquals(personDTO.getAge(), age);
    }

    @DisplayName("Get status 200 when send http's GET request by ID successfully")
    @Test
    public void getStatus200_whenGetPerson() {

        // prepare
        String name = "Igor";
        String surname = "Malianov";

        LocalDate birthDay = LocalDate.of(1978, 05, 22);
        LocalDate today = LocalDate.now();

        long id = createTestPerson(name, surname, birthDay).getId();

        // when
        ResponseEntity<PersonDTO> response = template.getForEntity("/persons/" + id, PersonDTO.class);

        // then
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals(response.getBody().getName(), "Igor");
    }

    @DisplayName("Get correct http's Options when send http's GET request by ID")
    @Test
    public void getResourceHttpOptions() {

        // when
        HttpMethod[] supportedMethods = {HttpMethod.GET, HttpMethod.HEAD, HttpMethod.OPTIONS};
        Set<HttpMethod> optionsForAllow = template.optionsForAllow("/persons/1");

        // then
        Assertions.assertTrue(optionsForAllow.containsAll(Arrays.asList(supportedMethods)));
    }

    private Person createTestPerson(String name, String surname, LocalDate birthDay) {
        Person person = new Person(name, surname, birthDay);
        return repository.save(person);
    }
}
