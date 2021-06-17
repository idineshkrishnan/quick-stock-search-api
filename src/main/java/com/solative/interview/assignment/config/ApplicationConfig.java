package com.solative.interview.assignment.config;

import com.google.gson.Gson;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Executor;

/**
 * @author Dinesh Krishnan
 */

@Configuration
@EnableAsync
public class ApplicationConfig {

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @Bean
    public Gson getGson() {
        return new Gson();
    }

    @Bean
    public Executor asyncExecutor() {
        return new ThreadPoolTaskExecutor();
    }
}
