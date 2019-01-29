package com.seva.kinder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;

@Component
public class MailController {

    @Autowired
    private JavaMailSender mailSender;

    public void sendMessage(String email,String name) {
        try {
            sendEmail(email,name);
        }catch(Exception ex) {
            System.out.println(ex.getMessage());
        }

    }
    private void sendEmail(String email,String name) throws Exception{
        Thread thread=new Thread(){
            @Override
            public void run() {
                try{
                    String msg="Happy BirthDay Dear. "+name.toUpperCase()+"  !!! Have a Great Day. \n \n Thank You \n Seva Development ";
                    MimeMessage message = mailSender.createMimeMessage();
                    MimeMessageHelper helper = new MimeMessageHelper(message);
                    helper.setTo(email);
                    helper.setText(msg);
                    helper.setSubject("BirthDay");
                    mailSender.send(message);

                }catch (Exception e){}
            }
        };
        thread.start();

    }

}
