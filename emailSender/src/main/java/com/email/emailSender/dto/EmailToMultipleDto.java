package com.email.emailSender.dto;

import lombok.Data;

@Data
public class EmailToMultipleDto {
    private String [] to;
    private String subject;
    private String body;
}
