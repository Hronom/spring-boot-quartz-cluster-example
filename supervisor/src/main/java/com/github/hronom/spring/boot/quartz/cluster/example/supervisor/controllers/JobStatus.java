package com.github.hronom.spring.boot.quartz.cluster.example.supervisor.controllers;

public class JobStatus {
    public final String id;
    public final boolean running;

    public JobStatus(String id, boolean running) {
        this.id = id;
        this.running = running;
    }
}
