package com.person.service;

import com.person.model.PersonDTO;
import com.person.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;

@Service
public class PersonDTOService {

    PersonRepository repository;

    public PersonDTOService(PersonRepository repository) {
        this.repository = repository;
    }

    public PersonDTO findById(Long id) {

        return repository.findById(id)
                .map(p -> new PersonDTO(p.getName(), p.getSurname(), getAgeByBirthdayDate(p.getBirthday())))
                .orElseThrow(() -> new PersonNotFoundException(id));
    }

    private Integer getAgeByBirthdayDate(LocalDate birthDate) {
        return Period.between(birthDate, LocalDate.now()).getYears();
    }
}
