package com.pragma.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {
        "com.pragma",
        "com.pragma.service",
        "com.pragma.adapters",
        "com.pragma.endpoints",
        "com.pragma.persistence.adapter"
})
@EnableJpaRepositories("com.pragma.persistence.dao")
@EntityScan("com.pragma.entity")
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
