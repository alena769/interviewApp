package com.example.demo.models.email;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;

@Configuration
public class Email {

    @Bean
    public SimpleMailMessage templateSimpleMessage() {
       SimpleMailMessage message = new SimpleMailMessage();
       message.setText("Thank you for signing up!");

        return message;
    }
}
