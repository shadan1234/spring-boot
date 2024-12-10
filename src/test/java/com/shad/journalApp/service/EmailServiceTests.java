package com.shad.journalApp.service;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.shad.journalApp.services.EmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTests {

    @Autowired
    private EmailService emailService;

    @Test
    void testSendEmail() {
        emailService.sendEmail("manpopeye42@gmail.com","Testerrrrrr","vah bete padhai karo mc sala din bhar ladki ka chinta leta hai");
    }
}
