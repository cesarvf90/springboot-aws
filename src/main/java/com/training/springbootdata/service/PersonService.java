package com.training.springbootdata.service;

import com.training.springbootdata.model.Person;
import com.training.springbootdata.repository.PersonRepository;
import javax.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> findAllPersons() {
        return personRepository.findAll();
    }

    public Optional<Person> findById(Long id) {
        return personRepository.findById(id);
    }

    public Person createPerson(@RequestBody Person person) {
        return personRepository.save(person);
    }

    public List<Person> searchByFirstName(String firstName) {
        List<Person> persons = personRepository.findByFirstName(firstName);

        if (persons == null || persons.isEmpty()) {
            throw new EntityNotFoundException("No persons found by first name: " + firstName);
        }

        return persons;
    }
    public List<Person> searchByLastName(String lastName) {
        List<Person> persons = personRepository.findByLastName(lastName);

        if (persons == null || persons.isEmpty()) {
            throw new EntityNotFoundException("No persons found by last name: " + lastName);
        }

        return persons;
    }

    public List<Person> searchByCity(String city) {
        List<Person> persons = personRepository.findByCity(city);

        if (persons == null || persons.isEmpty()) {
            throw new EntityNotFoundException("No persons found by city: " + city);
        }

        return persons;
    }

    public List<Person> searchByAge(int age) {
        List<Person> persons = personRepository.findByAge(age);

        if (persons == null || persons.isEmpty()) {
            throw new EntityNotFoundException("No persons found by age: " + age);
        }

        return persons;
    }


}