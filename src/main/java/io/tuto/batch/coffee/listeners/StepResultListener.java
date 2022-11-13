package io.tuto.batch.coffee.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

public class StepResultListener implements StepExecutionListener {
    Logger logger = LoggerFactory.getLogger(StepResultListener.class);

    @Override
    public void beforeStep(StepExecution stepExecution) {
        logger.info(" *** Called beforeStep(). stepName : {} ", stepExecution.getStepName());
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        logger.info(" *** Called afterStep() -- ExistStatus : {}", stepExecution.getExitStatus());
        return stepExecution.getExitStatus();
    }
}
