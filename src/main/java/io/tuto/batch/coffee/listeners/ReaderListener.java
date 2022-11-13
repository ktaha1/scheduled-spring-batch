package io.tuto.batch.coffee.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ItemReadListener;

public class ReaderListener  implements ItemReadListener<String> {

    private static final Logger logger = LoggerFactory.getLogger(ReaderListener.class);

    @Override
    public void beforeRead() {
        logger.info(" *** Before reading an item");
    }

    @Override
    public void afterRead(String item) {
        logger.info(" *** After reading an item: {}", item);
    }

    @Override
    public void onReadError(Exception ex) {
        logger.error(" *** Error occurred while reading an item!");
    }
}