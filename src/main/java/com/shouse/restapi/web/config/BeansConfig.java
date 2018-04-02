package com.shouse.restapi.web.config;

import com.shouse.restapi.web.service.UserService;
import com.shouse.restapi.web.service.UserServiceRest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


@Configuration
public class BeansConfig {

    @Bean
    public UserService usersService(){
        return new UserServiceRest();
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
}
