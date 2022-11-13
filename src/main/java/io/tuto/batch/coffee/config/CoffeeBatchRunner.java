package io.tuto.batch.coffee.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CoffeeBatchRunner {
    private static final Logger logger =
            LoggerFactory.getLogger(CoffeeBatchRunner.class);

    @Autowired
    private JobLauncher jobLauncher;
    @Autowired
    @Qualifier("coffeeJob")
    private Job job;

    @Scheduled(cron = "0 */1 * * * ?")
    public void perform() throws Exception{
        logger.info("Start executing coffee job ....");
        JobParameters param = new JobParametersBuilder()
                .addString("JobID", String.valueOf(System.currentTimeMillis()))
                        .toJobParameters();
        jobLauncher.run(job,param);
        logger.info("End executing coffee job ....");
    }
}
