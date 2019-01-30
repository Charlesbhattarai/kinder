package com.seva.kinder;

import com.seva.kinder.repository.PersonRepository;
import com.seva.kinder.service.PersonService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest
public class KinderApplicationTests {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    PersonService personService;


    @Test
    public void contextLoads() throws Exception {
        when(personService.getAll()).thenReturn(Collections.emptyList());

       MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/userlist").accept(MediaType.APPLICATION_JSON)
        ).andReturn();
        System.out.println(mvcResult.getResponse());
        verify(personService.getAll());
    }

}

