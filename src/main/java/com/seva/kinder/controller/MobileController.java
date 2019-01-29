package com.seva.kinder.controller;

import com.seva.kinder.entity.MobileDetail;
import com.seva.kinder.entity.Person;
import com.seva.kinder.service.MobileService;
import com.seva.kinder.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MobileController {
    @Autowired
    private MobileService mobileService;
    @Autowired
    private PersonService personService;
    @PostMapping("/mobileSave")
    public String saveMobile(Model model, @ModelAttribute MobileDetail mobileDetail,
                             RedirectAttributes redirectAttributes,
                             @RequestParam("pId") Long pId){
        Person person= personService.getById(pId);
        mobileDetail.setPerson(person);
        mobileService.saveMobile(mobileDetail);
        redirectAttributes.addFlashAttribute("flash", "Saved Mobile Number "+mobileDetail.getMobileNumber()+" Successfully!");
        return "redirect:/";
    }
    // for mobile show
    @GetMapping("/getmobile")
    public String getMobileView(Model model,@RequestParam Long id){
        System.out.println(id);
        model.addAttribute("mshow","active");
        model.addAttribute("id",id);
        return "home/edit";
    }

}
