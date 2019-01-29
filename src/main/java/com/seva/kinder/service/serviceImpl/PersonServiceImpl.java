package com.seva.kinder.service.serviceImpl;

import com.seva.kinder.entity.Person;
import com.seva.kinder.repository.PersonRepository;
import com.seva.kinder.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {
    @Autowired
    PersonRepository personRepository;
    
    @Override
    public List<Person> getAll() {
        List<Person> pList=personRepository.findAll();
//		List<Person> pList=personRepository.findAll(pageable);

//		return pList;
        for(Person p:pList) {

            LocalDate now = LocalDate.now();
            Period diff = Period.between(p.getDob(), now);
//					 		
            p.setAge(diff.getYears()+" Yrs. "+diff.getMonths()+" Month. "+diff.getDays()+" days old.");
        }
        return pList;

    }

    @Override
    public Person savePerson(Person person) {
        return personRepository.save(person);
    }

    @Override
    public Person getById(long id) {
        return personRepository.getOne(id);
    }

    @Override
    public Person update(Person person) {
        return null;
    }

    @Override
    public String delete(long id) {
        personRepository.deleteById(id);
        return "Deleted Successfully ";
    }

    @Override
    public Person getByEmail(String email) {
        return personRepository.getByEmail(email);
    }
}
