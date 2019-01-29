package com.seva.kinder;

import com.seva.kinder.controller.MailController;
import com.seva.kinder.entity.Person;
import com.seva.kinder.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.List;
@Component
public class Scheduler {
    @Autowired
    private PersonService personService;
    @Autowired
    private MailController mailController;

    @Scheduled(cron = "0 57 15 * * *")
    public void birthdayTime() throws MessagingException {
        List<Person> personList = personService.getAll();
//        personList.forEach(e -> System.out.println(e.getDob()));
        LocalDate now = LocalDate.now();
        int month = now.getMonthValue();
        int day = now.getDayOfMonth();

        int dobMonth;
        int dobDay;
        for(Person l:personList){
            dobMonth=l.getDob().getMonthValue();
            dobDay=l.getDob().getDayOfMonth();
            if(month==dobMonth && day==dobDay) {
                mailController.sendMessage(l.getEmail(),l.getFullName());
                System.out.println("mail has been saved:");
            }


        }

//        MimeMessage message = mailSender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(message);
//            helper.setTo("bhattaraicharles@gmail.com");
////        helper.setTo("anilthing2055@gmail.com");
//        helper.setText("How are you friend?");
//        helper.setSubject("Hi");
//        mailSender.send(message);
//        System.out.println("sdfasdfsdf");
    }

}
