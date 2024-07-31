package com.aop.config;

import com.aop.service.PaymentService;
import com.aop.service.PaymentServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppBean {

    @Bean
    PaymentService paymentService(){
        return  new PaymentServiceImpl();
    }
}
