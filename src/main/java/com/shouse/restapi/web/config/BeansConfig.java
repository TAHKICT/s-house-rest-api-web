package com.shouse.restapi.web.config;

import com.shouse.restapi.web.communicators.CommunicatorWithCore;
import com.shouse.restapi.web.communicators.CommunicatorWithCoreRestAPI;
import com.shouse.restapi.web.processors.ResponseFromCoreProcessor;
import com.shouse.restapi.web.service.core.CoreService;
import com.shouse.restapi.web.service.core.CoreServiceImpl;
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
    public CommunicatorWithCore communicatorWithCore(RestTemplate restTemplate){
        return new CommunicatorWithCoreRestAPI(restTemplate);
    }

    @Bean
    public ResponseFromCoreProcessor responseFromCoreProcessor (CommunicatorWithCore communicatorWithCore){
        return new ResponseFromCoreProcessor(communicatorWithCore);
    }

    @Bean
    public UserService usersService(CommunicatorWithCore communicatorWithCore){
        return new UserService(communicatorWithCore);
    }

    @Bean
    public CoreService coreService(){
        return new CoreServiceImpl();
    }

}
