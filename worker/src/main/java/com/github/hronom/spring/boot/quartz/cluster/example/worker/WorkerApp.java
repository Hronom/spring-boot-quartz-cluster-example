package com.github.hronom.spring.boot.quartz.cluster.example.worker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WorkerApp {
    public static void main(String[] args) {
        SpringApplication.run(WorkerApp.class, args);
    }
}
