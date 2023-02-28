package com.training.springbootdata.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.training.springbootdata.model.Person;
import com.training.springbootdata.service.PersonService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PersonController.class)
public class PersonControllerTest {

    @MockBean
    PersonService personService;

    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetPersonByIdSuccess() throws Exception {
        Person person = new Person("MockName", "MockLastName", "Malmo", 25);

        when(personService.findById(1L)).thenReturn(Optional.of(person));

        mockMvc.perform(get("/persons/1")).
                andExpect(status().isOk());
    }

    @Test
    public void testGetPersonByIdNotFound() throws Exception {
        Person person = new Person("MockName", "MockLastName", "Malmo", 25);

        when(personService.findById(1L)).thenReturn(Optional.of(person));

        mockMvc.perform(get("/persons/2")).
                andExpect(status().isNotFound());
    }
}


