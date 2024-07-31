package com.aop;

import com.aop.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AspectOrientedApplication {

	public static void main(String[] args) {
		SpringApplication.run(AspectOrientedApplication.class, args);
		System.out.println("Aspect Oriented Application started");

	}

}
