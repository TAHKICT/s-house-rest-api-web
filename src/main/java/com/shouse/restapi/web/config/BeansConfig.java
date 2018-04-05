package com.shouse.restapi.web.config;

import com.shouse.restapi.web.service.core.CoreService;
import com.shouse.restapi.web.service.core.CoreServiceImpl;
import com.shouse.restapi.web.service.user.UserService;
import com.shouse.restapi.web.service.user.UserServiceRest;
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
    public CoreService coreService(){
        return new CoreServiceImpl();
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
}
