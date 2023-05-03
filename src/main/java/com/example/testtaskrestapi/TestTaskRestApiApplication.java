package com.example.testtaskrestapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TestTaskRestApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestTaskRestApiApplication.class, args);
    }

}
