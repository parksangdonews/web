package net.thethirdplace.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AppContext {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
