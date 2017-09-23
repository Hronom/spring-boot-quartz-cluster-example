package com.github.hronom.spring.boot.quartz.cluster.example.supervisor.service;

import com.github.hronom.spring.boot.quartz.cluster.example.common.job.TestJob1;
import com.github.hronom.spring.boot.quartz.cluster.example.supervisor.controllers.JobStatus;

import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerKey;
import org.quartz.impl.matchers.GroupMatcher;
import org.quartz.utils.Key;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

@Service
public class JobsService {
    private final String groupName = "normal-group";

    private final Set<String> jobsIds = ConcurrentHashMap.newKeySet();

    private final Scheduler scheduler;

    @Autowired
    public JobsService(SchedulerFactoryBean schedulerFactory) {
        this.scheduler = schedulerFactory.getScheduler();
    }

    public List<String> startNewJobs(int jobs) throws SchedulerException {
        LinkedList<String> list = new LinkedList<>();
        for (int i = 0; i < jobs; i++) {
            list.add(startNewJob());
        }
        return list.stream().sorted(Comparator.naturalOrder()).collect(Collectors.toList());
    }

    public String startNewJob() throws SchedulerException {
        String id = UUID.randomUUID().toString();

        // define the job and tie it to our HelloJob class
        JobDetail job = newJob(TestJob1.class)
            .withIdentity(id, groupName)
            // http://www.quartz-scheduler.org/documentation/quartz-2.2.x/configuration/ConfigJDBCJobStoreClustering.html
            // https://stackoverflow.com/a/19270566/285571
            .requestRecovery(true)
            .build();

        Trigger trigger = scheduler.getTrigger(new TriggerKey(id + "-trigger", groupName));

        if (trigger == null) {
            // Trigger the job to run now, and then every 40 seconds
            trigger =
                newTrigger()
                    .withIdentity(id + "-trigger", groupName)
                    .startNow()
                    .withSchedule(
                        simpleSchedule()
                            .withIntervalInSeconds(30)
                    ).build();
        }

        // Tell quartz to schedule the job using our trigger
        scheduler.scheduleJob(job, trigger);
        return id;
    }

    public boolean deleteJob(String id) throws SchedulerException {
        return scheduler.deleteJob(new JobKey(id, groupName));
    }

    public List<String> getJobs() throws SchedulerException {
        return scheduler
            .getJobKeys(GroupMatcher.jobGroupEquals(groupName))
            .stream()
            .map(Key::getName)
            .sorted(Comparator.naturalOrder())
            .collect(Collectors.toList());
    }

    public List<JobStatus> getJobsStatuses() throws SchedulerException {
        LinkedList<JobStatus> list = new LinkedList<>();
        for (JobKey jobKey : scheduler.getJobKeys(GroupMatcher.jobGroupEquals(groupName))) {
            JobDetail jobDetail = scheduler.getJobDetail(jobKey);
            List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobDetail.getKey());
            for (Trigger trigger : triggers) {
                Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
                if (Trigger.TriggerState.COMPLETE.equals(triggerState)) {
                    list.add(new JobStatus(jobKey.getName(), true));
                } else {
                    list.add(new JobStatus(jobKey.getName(), false));
                }
            }
        }
        list.sort(Comparator.comparing(o -> o.id));
        return list;
    }
}
