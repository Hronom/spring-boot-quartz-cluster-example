package com.github.hronom.spring.boot.quartz.cluster.example.supervisor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SupervisorApp {
    public static void main(String[] args) {
        SpringApplication.run(SupervisorApp.class, args);
    }
}
