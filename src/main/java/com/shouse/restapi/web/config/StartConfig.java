package com.shouse.restapi.web.config;

import com.shouse.restapi.web.processors.ResponseFromCoreProcessor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

@Configuration
public class StartConfig {

    @Bean
    public TaskExecutor taskExecutor(){
        return new SimpleAsyncTaskExecutor();
    }

    @Bean
    public CommandLineRunner controllerRunner(TaskExecutor taskExecutor, ResponseFromCoreProcessor responseFromCoreProcessor){
        return args -> taskExecutor.execute(responseFromCoreProcessor);
    }
}
