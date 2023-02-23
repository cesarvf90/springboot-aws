package com.training.springbootdata.controller;

import java.util.List;
import java.util.Optional;

import com.training.springbootdata.model.Person;
import com.training.springbootdata.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
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

    @GetMapping("/{id}")
    public ResponseEntity<Person> getElementById(@PathVariable Long id) {
        Optional<Person> elementOptional = personRepository.findById(id);
        log.info("Element found by id: " + elementOptional);
        if (elementOptional.isPresent()) {
            return ResponseEntity.ok(elementOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("")
    public Person createPerson (@RequestBody Person person) {
        return personRepository.save(person);
    }
}
