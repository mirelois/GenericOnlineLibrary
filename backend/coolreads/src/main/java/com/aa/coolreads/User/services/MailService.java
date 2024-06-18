package com.aa.coolreads.User.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Profile("!test")
public class MailService { // Prod
    private final JavaMailSender mailSender;

    @Value("${coolreads.mail}")
    private String coolReadsMail;

    @Autowired
    public MailService(JavaMailSender mailSender){
        this.mailSender = mailSender;
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

    public void sendAccountCreationMail(String to){
        String subject = "CoolReads Account Creation";
        String body = """
                Welcome aboard to CoolReads!
                Best regards,
                The CoolReads Team""";

        sendEmail(this.coolReadsMail, to, subject, body);
    }
}
