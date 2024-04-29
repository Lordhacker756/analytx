package com.umbrella.projectumbrella;

import com.umbrella.projectumbrella.dto.MailBody;
import com.umbrella.projectumbrella.services.EmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SendEmailTest {

    @Autowired
    private EmailService emailService;

    @Test
    void sendEmail() {
        System.out.println("Sending email");
        MailBody mb = new MailBody("utkarshmishra2001@gmail.com", "utkarsh.mishra@wobb.ai", "This is a test mail", "Test mail from springboot");
        emailService.sendSimpleMessage(mb);
    }

}
