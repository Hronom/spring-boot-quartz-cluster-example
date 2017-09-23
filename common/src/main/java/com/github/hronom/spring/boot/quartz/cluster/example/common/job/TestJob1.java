package com.github.hronom.spring.boot.quartz.cluster.example.common.job;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

import java.util.concurrent.TimeUnit;

@DisallowConcurrentExecution
public class TestJob1 implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        System.out.println(
            "Run job with id " + jobExecutionContext.getJobDetail().getKey().getName()
        );
        try {
            Thread.sleep(TimeUnit.MINUTES.toMillis(1));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(
            "Complete job with id " + jobExecutionContext.getJobDetail().getKey().getName()
        );
    }
}
