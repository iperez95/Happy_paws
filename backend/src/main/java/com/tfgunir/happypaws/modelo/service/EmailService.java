package com.tfgunir.happypaws.modelo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.tfgunir.happypaws.modelo.entities.Protectora;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailService {

    @Autowired
    JavaMailSender javaMailSender;

    // public EmailService(JavaMailSender emailSender) {
    //     this.emailSender = emailSender;
    // }

    public void sendMail(String toEmail, String subject, String message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo("happypawsunir@gmail.com");
        mailMessage.setSubject(subject);
        mailMessage.setText(message);
        mailMessage.setFrom("happypawsunir@gmail.com");

        javaMailSender.send(mailMessage);
    }

     public void sendMailToProtectora(Protectora protectora, String subject, String text) {
        sendMail(protectora.getEmail(), subject, text);
    }
    
}
