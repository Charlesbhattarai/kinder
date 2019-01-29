package com.seva.kinder.repository;

import com.seva.kinder.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PersonRepository extends JpaRepository<Person,Long> {

    @Query("SELECT p FROM  Person  p WHERE p.email = ?1")
    Person getByEmail(String email);
}
