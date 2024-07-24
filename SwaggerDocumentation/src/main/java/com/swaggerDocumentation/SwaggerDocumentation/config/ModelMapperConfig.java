package com.swaggerDocumentation.SwaggerDocumentation.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

//    providing object for model mapper
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}