package com.tfgunir.happypaws.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tfgunir.happypaws.modelo.service.EmailService;

@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/enviarCorreo")
    public String  contactoProtectora(@RequestParam String formEmail, @RequestParam String subject, @RequestParam String message) {
        emailService.sendMail(formEmail, subject, message);
        return "Email enviado correctamente";
    }
}
