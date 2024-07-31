package com.email.emailSender.controller;

import com.email.emailSender.dto.EmailDto;
import com.email.emailSender.dto.EmailToMultipleDto;
import com.email.emailSender.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping
    public void sendEmail(@RequestBody EmailDto emailDto) {
        emailService.sendEmail(emailDto.getTo(), emailDto.getSubject(), emailDto.getBody());
    }

    @PostMapping("/sendTo")
    public void sendToEmail(@RequestBody EmailToMultipleDto emailDto) {
        emailService.sendEmail(emailDto.getTo(), emailDto.getSubject(), emailDto.getBody());
    }

    @PostMapping("/sendWithHtml")
    public void sendWithHtmlEmail(@RequestBody EmailDto emailDto) {
        emailService.sendEmailWithHtml(emailDto.getTo(), emailDto.getSubject(), emailDto.getBody());
    }
}
