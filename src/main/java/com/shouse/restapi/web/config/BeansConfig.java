package com.shouse.restapi.web.config;

import com.shouse.restapi.web.communicators.CommunicatorWithBrain;
import com.shouse.restapi.web.communicators.CommunicatorWithBrainRestAPI;
import com.shouse.restapi.web.processors.ResponseFromCoreProcessor;
import com.shouse.restapi.web.service.UserService;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


@Configuration
public class BeansConfig {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public UserService userService(CommunicatorWithBrainRestAPI communicatorWithCore){
        return new UserService(communicatorWithCore);
    }

    @Bean
    public ResponseFromCoreProcessor responseFromCoreProcessor (CommunicatorWithBrain communicatorWithBrain, UserService userService){
        return new ResponseFromCoreProcessor(communicatorWithBrain, userService);
    }

}
