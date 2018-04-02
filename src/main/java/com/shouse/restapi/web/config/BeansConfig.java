package com.shouse.restapi.web.config;

import com.shouse.restapi.web.service.UserService;
import com.shouse.restapi.web.service.UserServiceRest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfig {

    @Bean
    public UserService usersService(){
        return new UserServiceRest();
    }
}
