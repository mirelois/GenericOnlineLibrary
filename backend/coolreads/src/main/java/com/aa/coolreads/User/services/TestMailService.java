package com.aa.coolreads.User.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("test")
public class TestMailService extends MailService{

    private static final Logger logger = LoggerFactory.getLogger(TestMailService.class);

    public TestMailService() {
        super(null);
    }

    @Override
    public void sendEmail(String from, String to, String subject, String body) {
        logger.info("TestMailService: sendEmail called");
    }

    @Override
    public void sendNotificationMail(String to, String notificationType) {
        logger.info("TestMailService: sendNotificationMail called");
    }

    @Override
    public void sendAccountCreationMail(String to) {
        logger.info("TestMailService: sendAccountCreationMail called");
    }
}
