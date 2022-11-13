package io.tuto.batch.coffee.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

public class JobCoffeeListener implements JobExecutionListener {
    Logger logger = LoggerFactory.getLogger(JobCoffeeListener.class);

    public void beforeJob(JobExecution jobExecution) {
        logger.info(" *** Called beforeJob() -- createTime {} ", jobExecution.getCreateTime());
    }

    public void afterJob(JobExecution jobExecution) {
        logger.info(" *** Called afterJob() -- ExistStatuts {} -- endTime {}",
                jobExecution.getStatus(), jobExecution.getEndTime());
    }
}
