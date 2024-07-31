package com.actuator.ActuatorsApp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/controller")
public class CustomController {

    @GetMapping
    public String hello() {
        return " Hello Brother !";
    }
}
