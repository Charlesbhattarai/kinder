package com.seva.kinder.api;

import com.seva.kinder.entity.Person;
import com.seva.kinder.repository.PersonRepository;
import com.seva.kinder.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RestTestApi {
    @Autowired
    private PersonService personService;
    @RequestMapping(method = RequestMethod.GET, value = "/userlist", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Person> getUserInfo(HttpServletRequest request){
        List<Person> personList =null;
        String key=request.getHeader("seva");
        if(key !=null && key.equalsIgnoreCase("dev")){
            personList=personService.getAll();
        }
        return personList;
    }
}
