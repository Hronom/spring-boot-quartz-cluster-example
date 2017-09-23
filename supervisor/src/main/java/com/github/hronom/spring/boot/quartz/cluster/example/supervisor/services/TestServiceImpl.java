package com.github.hronom.spring.boot.quartz.cluster.example.supervisor.services;

import com.github.hronom.spring.boot.quartz.cluster.example.common.service.TestService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class TestServiceImpl implements TestService {
    private final Log logger = LogFactory.getLog(getClass());

    public void run(String id) {
        logger.info("Running job on supervisor, job id " + id);
        try {
            Thread.sleep(TimeUnit.MINUTES.toMillis(1));
        } catch (InterruptedException e) {
            logger.error("Error", e);
        }
        logger.info("Completed job on supervisor, job id " + id);
    }
}
