package com.github.hronom.spring.boot.quartz.cluster.example.common.job;

import com.github.hronom.spring.boot.quartz.cluster.example.common.service.TestService;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

@DisallowConcurrentExecution
public class TestJob1 implements Job {

    @Autowired
    private TestService testService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            String id = jobExecutionContext.getJobDetail().getKey().getName();
            testService.run(id);
        } catch (Exception e) {
            throw new JobExecutionException(e);
        }
    }
}
