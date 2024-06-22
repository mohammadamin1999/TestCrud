package com.example.testcrud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TestCrudApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestCrudApplication.class, args);
    }

}
