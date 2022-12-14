package com.person.service;

public class PersonNotFoundException extends RuntimeException {

    public PersonNotFoundException(Long id) {
        super("Could not find employee " + id);
    }
}