package com.seva.kinder.controller;//package com.hamro.kinder.controller;

import com.seva.kinder.entity.Person;
import com.seva.kinder.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;


@Controller
public class MainController {
    @Autowired
    private PersonService personService;

    @RequestMapping(method=RequestMethod.GET,value="/")
    public String getMain(Model model) {
        List<Person> pLists=personService.getAll();
        model.addAttribute("list", pLists);
        return "home/user";
    }
    @RequestMapping(method=RequestMethod.POST,value="/save")
    public String savePerson(Model model, @Valid @ModelAttribute Person person,
                             RedirectAttributes redirAttrs, BindingResult result) {

         if(result.hasErrors()) {

        }
        if (person.getFullName().isEmpty() || person.getFullName().equals(null)) {
            redirAttrs.addFlashAttribute("error", "Error!");
        } else {
            personService.savePerson(person);
            redirAttrs.addFlashAttribute("flash", "Added Successfully!");
        }
        return "redirect:/";
    }
    @RequestMapping("/getAllData")
    @ResponseBody
    public String getAllData() {
        return "index";
    }

}
