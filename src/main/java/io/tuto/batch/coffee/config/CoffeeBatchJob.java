package io.tuto.batch.coffee.config;

import io.tuto.batch.coffee.listeners.JobCoffeeListener;
import io.tuto.batch.coffee.listeners.WriterCoffeeListener;
import io.tuto.batch.coffee.model.Coffee;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class CoffeeBatchJob {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Value("${file.input.path}")
    private String filePath;

    // JOB
    @Bean
    public Job job(Step step){
        return jobBuilderFactory.get("coffeeJob")
                .incrementer(new RunIdIncrementer())
                .listener(new JobCoffeeListener())
                .flow(step)
                .end()
                .build();
    }

    // STEP
    @Bean
    public Step step(JdbcBatchItemWriter<Coffee> writer){
        return stepBuilderFactory.get("primaryStep")
                .<Coffee,Coffee>chunk(10)
                .reader(reader())
                //.listener(new StepResultListener())
                .writer(writer)
                //.listener(new ChunkCoffeeListener())
                //.listener(new ReaderListener())
                .listener(new WriterCoffeeListener())
                .build();
    }


    // READER
    @Bean
    public FlatFileItemReader<Coffee> reader() {

        return new FlatFileItemReaderBuilder<Coffee>()
                .name("coffeeItemReader")
                .resource(new FileSystemResource(filePath))
                .delimited()
                .names("brand", "origin", "characteristics")
                .fieldSetMapper(new BeanWrapperFieldSetMapper<Coffee>() {{
                    setTargetType(Coffee.class);
                }})
                .build();
    }



    // WRITER
    @Bean
    public JdbcBatchItemWriter<Coffee> writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Coffee>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO coffee (brand, origin, characteristics) VALUES (:brand, :origin, :characteristics)")
                .dataSource(dataSource)
                .build();
    }


    /*
    @Bean
    public ConsoleItemWriter<Coffee> writer()
    {
        return new ConsoleItemWriter<Coffee>();
    }}*/
}
