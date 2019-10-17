package com.project.netflixapi;

import com.project.netflixapi.services.FeignRestClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TestingNetflix implements CommandLineRunner {

    private  final FeignRestClient feignRestClient;

    public TestingNetflix(FeignRestClient feignRestClient) {
        this.feignRestClient = feignRestClient;
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
