package com.training.springbootdata.repository;

import com.training.springbootdata.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    List<Person> findByFirstName(String firstName);
    List<Person> findByLastName(String lastName);
    List<Person> findByCity(String city);
    List<Person> findByAge(int age);
    Person findById(long id);

}
