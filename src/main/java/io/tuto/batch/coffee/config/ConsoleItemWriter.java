package io.tuto.batch.coffee.config;

import org.springframework.batch.item.ItemWriter;

import java.util.List;

public class ConsoleItemWriter<T> implements ItemWriter<T> {
    @Override
    public void write(List<? extends T> items) {
        for (T item : items) {
            System.out.println(" *** Writing Item on the wall : "+item);
        }
    }
}