package com.springBootKafka.KafKaApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafKaApplication {

	public static void main(String[] args) {

		SpringApplication.run(KafKaApplication.class, args);

		System.out.println("kafka application started.....");

	}

}
