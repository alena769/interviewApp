package com.example.demo.services.email;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@AllArgsConstructor
public class EmailSenderImpl implements EmailSender {

    private final static Logger LOGGER = LoggerFactory.getLogger(EmailSenderImpl.class);
    private final static String FAIL_TO_SEND_EMAIL = "failed to send confirmation email to %s";

    private JavaMailSender mailSender;
    public SimpleMailMessage emailTemplate;

    @Override
    @Async
    public void sendConfirmationEmail(String email) {


        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setTo(email);
            helper.setSubject("Test confirmaiton email");
            helper.setText(emailTemplate.getText());
            helper.setFrom("noreply@gmail.com"); // dummy email
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            LOGGER.error("failed to send email", e);
            throw new IllegalStateException(String.format(FAIL_TO_SEND_EMAIL, email));
        }
    }
}
