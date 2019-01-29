package com.seva.kinder.controller;//package com.hamro.kinder.controller;

import com.seva.kinder.entity.Person;
import com.seva.kinder.service.MobileService;
import com.seva.kinder.service.PersonService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;


@Controller
public class PersonController {
    @Autowired
    private PersonService personService;
    @Autowired
    private MobileService mobileService;

//    for get user page
    @RequestMapping(method=RequestMethod.GET,value="/")
    public String getMain(Model model) {
        List<Person> pLists=personService.getAll();
        model.addAttribute("list", pLists);
        return "home/person";
    }
//    for save
    @RequestMapping(method=RequestMethod.POST,value="/save")
    public String savePerson(Model model, @Valid @ModelAttribute Person person,
                             RedirectAttributes redirAttrs, BindingResult result, HttpServletRequest request) {
        System.out.println(request.getParameter("dob"));

         if(result.hasErrors()) {
             System.out.println(result);
        }
        Person p= personService.getByEmail(person.getEmail());
        if (p!=null) {
            redirAttrs.addFlashAttribute("error", "Email Address Already!");
        } else {
//            personService.savePerson(person);
            redirAttrs.addFlashAttribute("flash", "Added Successfully!");
        }
        return "redirect:/";
    }

//    for get edit data
    @RequestMapping(method=RequestMethod.GET,value="/edit/{id}")
    public String editUser(Model model,@PathVariable("id") Long id) {
        model.addAttribute("pModel",personService.getById(id));
        model.addAttribute("show","active");
        return "home/edit";

    }

//    for update
    @PostMapping("/update")
    public String updateUser(Model model,@ModelAttribute Person person,RedirectAttributes redirectAttributes){
         personService.savePerson(person);
        redirectAttributes.addFlashAttribute("flash", "Updated Successfully!");
         return "redirect:/";
    }

//    for delete
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id,RedirectAttributes redirectAttributes){

        String status=personService.delete(id);
        redirectAttributes.addFlashAttribute("flash",status);
        return "redirect:/";
    }

//    for getMobile number
    @GetMapping("/mobilelist")
    public String getMobileList(@RequestParam Long id,Model model){
       Person p= personService.getById(id);
        model.addAttribute("mobileList",p.getMobile());
        model.addAttribute("model",personService.getById(id));
        model.addAttribute("mobile","active");
        return "home/edit";
    }
}
