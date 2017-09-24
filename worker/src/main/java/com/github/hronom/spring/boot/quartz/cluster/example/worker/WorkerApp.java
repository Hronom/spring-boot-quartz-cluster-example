package com.github.hronom.spring.boot.quartz.cluster.example.worker;

import com.github.hronom.spring.boot.quartz.cluster.example.common.service.JobsListenerService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(JobsListenerService.class)
public class WorkerApp {
    public static void main(String[] args) {
        SpringApplication.run(WorkerApp.class, args);
    }
}
