package io.tuto.batch.coffee.listeners;

import io.tuto.batch.coffee.model.Coffee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ItemWriteListener;

import java.util.List;

public class WriterCoffeeListener implements ItemWriteListener<Coffee> {
    Logger logger = LoggerFactory.getLogger(WriterCoffeeListener.class);


    @Override
    public void beforeWrite(List<? extends Coffee> list) {
        logger.info(" *** beforeWrite -- Items  {}", list);
    }

    @Override
    public void afterWrite(List<? extends Coffee> list) {
        logger.info(" *** afterWrite");
    }

    @Override
    public void onWriteError(Exception e, List<? extends Coffee> list) {
        logger.info(" *** onWriteError");
    }
}
