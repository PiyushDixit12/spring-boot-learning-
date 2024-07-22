package com.example.blog_app_api.config;

import com.example.blog_app_api.services.UserService;
import com.example.blog_app_api.services.UserServiceImp;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

//    providing user service
    @Bean
    UserService provideUserService(){
        return new UserServiceImp();
    }

    @Bean
    ModelMapper provideModelMapper(){
        return new ModelMapper();
    }

}
