package com.person;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@DisplayName("Application startability")
@SpringBootTest
class SmokeTest {

    @Autowired
    private PersonRESTApplication controller;

    @Test
    void contextLoads() {
        Assertions.assertNotNull(controller);
    }
}