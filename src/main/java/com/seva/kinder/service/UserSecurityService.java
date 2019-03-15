package com.seva.kinder.service;

import com.seva.kinder.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserSecurityService implements UserDetailsService {

    @Autowired
    PersonService personService;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Person person = personService.getByEmail(email);

        if(null == person) {
            throw new UsernameNotFoundException("Username not found");
        }
        return person;
    }
}
