package com.seva.kinder.service;

import com.seva.kinder.entity.Person;

import java.util.List;

public interface PersonService {
    List<Person> getAll();
    Person savePerson(Person person);
    Person getById(long id);
    Person update(Person person);
    String delete(long id);
    Person getByEmail(String email);
}
