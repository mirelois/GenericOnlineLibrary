package com.aa.coolreads.User.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {
    private final JavaMailSender mailSender;

    private final String coolReadsMail;

    @Autowired
    public MailService(JavaMailSender mailSender){
        this.mailSender = mailSender;
        this.coolReadsMail = "no-reply@coolreads.com";
    }


    public void sendEmail(String from, String to, String subject, String body){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);

        this.mailSender.send(message);
    }

    public void sendNotificationMail(String to, String notificationType){
        sendEmail(this.coolReadsMail, to, notificationType, "You have a new " + notificationType);
    }

    public void sendAccountCreationMail(String to, String number, String password){
        String subject = "CoolReads Account Creation";
        String body = """
                Welcome aboard to CoolReads!
                Best regards,
                The CoolReads Team""";

        sendEmail(this.coolReadsMail, to, subject, body);
    }
}
