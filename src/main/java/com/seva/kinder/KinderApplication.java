package com.seva.kinder;

import com.seva.kinder.entity.Person;
import com.seva.kinder.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Date;

@SpringBootApplication
@EnableScheduling
public class KinderApplication {
    @Autowired
    private PersonRepository personRepository;

    public static void main(String[] args) {
        SpringApplication.run(KinderApplication.class, args);
    }
    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {

        return args -> {
            if(personRepository.findAll()==null)
            {
                Person person =new Person();
                person.setFullName("admin");
                person.setEmail("admin@gmail.com");

                person.setRole("admin");
                person.setAddress("admin");
                Date date=new Date();
                person.setDob("2018-01-01");
                personRepository.save(person);
                System.out.println("default");
            }

        };
    }
}

