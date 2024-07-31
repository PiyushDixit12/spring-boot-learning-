package com.email.emailSender.service;


import java.io.File;

public interface EmailService {

    void sendEmail(String to, String subject, String body);

    void sendEmail(String [] to, String subject, String body);

    void  sendEmailWithHtml(String to, String subject, String html);

}
