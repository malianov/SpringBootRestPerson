package com.person.controller;

import com.person.model.PersonDTO;
import com.person.service.PersonDTOService;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class PersonController {

    PersonDTOService service;

    public PersonController(PersonDTOService service) {
        this.service = service;
    }

    @GetMapping("/persons/{id}")
    EntityModel<PersonDTO> one(@PathVariable Long id) {
        PersonDTO personDTO = service.findById(id);

        return EntityModel.of(personDTO, linkTo(methodOn(PersonController.class).one(id)).withSelfRel());
    }
}