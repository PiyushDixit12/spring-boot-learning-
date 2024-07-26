package com.redis.ApiRedis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiRedisApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiRedisApplication.class, args);
		System.out.println("Redis application started....");
	}

}
