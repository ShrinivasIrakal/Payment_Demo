package com.payments.Config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class AppConfig {


    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
