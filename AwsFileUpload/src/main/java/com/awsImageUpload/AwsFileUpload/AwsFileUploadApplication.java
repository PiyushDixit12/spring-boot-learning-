package com.awsImageUpload.AwsFileUpload;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AwsFileUploadApplication {

	public static void main(String[] args) {
		SpringApplication.run(AwsFileUploadApplication.class, args);

		System.out.println("aws service started");
	}

}
