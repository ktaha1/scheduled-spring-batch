package io.tuto.batch.coffee.listeners;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ChunkListener;
import org.springframework.batch.core.scope.context.ChunkContext;

public class ChunkCoffeeListener implements ChunkListener {
    private static final Logger logger = LoggerFactory.getLogger(ChunkCoffeeListener.class);
    @Override
    public void afterChunk(ChunkContext context) {
        logger.info(" *** Called afterChunk().");
    }

    @Override
    public void beforeChunk(ChunkContext context) {
        logger.info(" *** Called beforeChunk().");
    }

    @Override
    public void afterChunkError(ChunkContext context) {
        logger.info(" *** Called afterChunkError().");
    }
}
