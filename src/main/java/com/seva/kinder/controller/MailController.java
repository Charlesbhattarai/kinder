package com.seva.kinder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.mail.internet.MimeMessage;

@Controller
public class MailController {

    @Autowired
    private JavaMailSender mailSender;

    @GetMapping("/mail")
    @ResponseBody
    public void sendMessage() {
        try {
            sendEmail();
//            return "Email Sent!";
        }catch(Exception ex) {
//            return "Error in sending email: "+ex;
        }

    }
    private void sendEmail() throws Exception{
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setTo("aruntamang365@gmail.com");
        helper.setText("How are you friend?");
        helper.setSubject("Hi");
        mailSender.send(message);

    }

}
