package com.example.demo.services.email;

import org.springframework.stereotype.Service;

@Service
public interface EmailSender {
    void sendConfirmationEmail(String email);
}
