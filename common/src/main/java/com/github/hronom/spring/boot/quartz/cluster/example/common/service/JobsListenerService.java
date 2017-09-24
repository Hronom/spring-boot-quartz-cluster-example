package com.github.hronom.spring.boot.quartz.cluster.example.common.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;
import org.springframework.stereotype.Service;

@Service
public class JobsListenerService implements JobListener {
    private final Log logger = LogFactory.getLog(getClass());

    @Override
    public String getName() {
        return "Main Listener";
    }

    @Override
    public void jobToBeExecuted(JobExecutionContext context) {
        logger.info("Job to be executed " + context.getJobDetail().getKey().getName());
    }

    @Override
    public void jobExecutionVetoed(JobExecutionContext context) {
        logger.info("Job execution vetoed " + context.getJobDetail().getKey().getName());
    }

    @Override
    public void jobWasExecuted(
        JobExecutionContext context, JobExecutionException jobException
    ) {
        logger.info(
            "Job was executed " +
            context.getJobDetail().getKey().getName() +
            (jobException != null ? ", with error" : "")
        );
    }
}
