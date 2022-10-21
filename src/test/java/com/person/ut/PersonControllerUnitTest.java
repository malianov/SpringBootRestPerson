package com.person.ut;

import com.person.controller.PersonController;
import com.person.model.PersonDTO;
import com.person.service.PersonDTOService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PersonController.class)
public class PersonControllerUnitTest {

    @MockBean
    PersonDTOService service;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void findPersonById() throws Exception {

        // prepare
        PersonDTO person = new PersonDTO("Igor", "Malianov", 22);

        // when
        Mockito.when(service.findById(1L)).thenReturn(person);

        // then
        mockMvc.perform(get("/persons/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", Matchers.is("Igor")))
                .andExpect(jsonPath("$.surname", Matchers.is("Malianov")))
                .andExpect(jsonPath("$.age", Matchers.is(22)));
    }
}
