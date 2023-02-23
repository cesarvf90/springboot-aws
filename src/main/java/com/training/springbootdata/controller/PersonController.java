package com.training.springbootdata.controller;

import java.util.List;

import com.training.springbootdata.model.Person;
import com.training.springbootdata.repository.PersonRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/persons")
public class PersonController {

    private final PersonRepository personRepository;

    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping("")
    public List<Person> getPersons() {
        return personRepository.findAll();
    }

    @PostMapping("")
    public Person createPerson (@RequestBody Person person) {
        return personRepository.save(person);
    }
}
