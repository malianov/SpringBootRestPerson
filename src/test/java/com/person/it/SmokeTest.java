package com.person.it;

import com.person.PersonRESTApplication;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@DisplayName("Application start ability")
@SpringBootTest
class SmokeTest {

    @Autowired
    private PersonRESTApplication controller;

    @Test
    void contextLoads() {
        Assertions.assertNotNull(controller);
    }
}