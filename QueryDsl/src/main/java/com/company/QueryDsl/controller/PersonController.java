package com.company.QueryDsl.controller;

import com.company.QueryDsl.entity.Person;
import com.company.QueryDsl.service.PersonService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/person")
public class PersonController {

    private final PersonService personService;

    @GetMapping()
    public List<Person> getPersons() {
        return personService.getUsers();
    }

    @PostMapping()
    public Person createPerson(@RequestBody Person user) {
        return personService.createUser(user);
    }

    @PutMapping("/update/{id}")
    public Person updatePerson(@PathVariable Integer id, @RequestBody Person person) {
        return personService.updateUser(person, id);
    }

    @DeleteMapping("{id}")
    public Long deletePerson(@PathVariable Integer id) {
        return personService.deletePerson(id);
    }
}
