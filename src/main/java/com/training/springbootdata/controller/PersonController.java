package com.training.springbootdata.controller;

import java.util.List;
import java.util.Optional;

import com.training.springbootdata.model.Person;
import com.training.springbootdata.service.PersonService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/persons")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @ApiOperation(value = "Get all the persons", notes = "Returns all the persons")
    @GetMapping("")
    public List<Person> getPersons() {
        return personService.findAllPersons();
    }

    @ApiOperation(value = "Get a person by id", notes = "Returns a person by their unique identifier")
    @GetMapping("/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable Long id) {
        Optional<Person> elementOptional = personService.findById(id);
        log.info("Person found by id: " + elementOptional);
        if (elementOptional.isPresent()) {
            return ResponseEntity.ok(elementOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @ApiOperation(value = "Search a person by parameters", notes = "Returns a list of persons who meet the search criteria")
    @GetMapping("/search")
    public ResponseEntity<List<Person>> searchPersonsByParameters(
            @RequestParam(name = "firstname", required = false) String firstName,
            @RequestParam(name = "lastname", required = false) String lastName,
            @RequestParam(name = "city", required = false) String city,
            @RequestParam(name = "age", required = false) Integer age) {
        List<Person> persons = null;

        if (firstName != null) {
            persons = personService.searchByFirstName(firstName);
        } else if (lastName != null) {
            persons = personService.searchByLastName(lastName);
        } else if (city != null) {
            persons = personService.searchByCity(city);
        } else if (age != null) {
            persons = personService.searchByAge(age);
        }

        if (persons == null || persons.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(persons);
        }
    }

    @ApiOperation(value = "Create a person", notes = "Create a person in the system")
    @PostMapping("")
    public Person createPerson(@RequestBody Person person) {
        return personService.createPerson(person);
    }
}
