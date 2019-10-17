package com.project.netflixapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class NetflixapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(NetflixapiApplication.class, args);
    }

}
