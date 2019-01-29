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

                    String msg="Dear<b> "+name.toUpperCase()+" </b>,<p> On Behalf of Seva Development we would like to wish you a Many many Happy returns of the day</p> <p style=color:red;><b>Happy Birthday and Have a Great Day.<b/></p>\n \n Thank You! <p> Seva Development </p>";
                    MimeMessage message = mailSender.createMimeMessage();
                    MimeMessageHelper helper = new MimeMessageHelper(message,true);
                    helper.setTo(email);
                    message.setContent(msg,"text/html");
                    helper.setText(msg);
                    helper.setSubject("BirthDay");
                    mailSender.send(message);

                }catch (Exception e){}
            }
        };
        thread.start();

    }

}
