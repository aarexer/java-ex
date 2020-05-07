package aarexer.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {
    @Value("${spring.mail.username}")
    private String username;

    private final JavaMailSender mailSender;

    public EmailSenderService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void send(String email, String subject, String text) {
        final SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(username);
        message.setSubject(subject);
        message.setText(text);
        message.setTo(email);

        mailSender.send(message);
    }

}
