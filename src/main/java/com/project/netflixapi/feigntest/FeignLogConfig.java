package com.project.netflixapi.feigntest;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class FeignLogConfig {

    @Bean
    Logger.Level  feignLoggerLevel(){
        return Logger.Level.FULL;
    }
}
