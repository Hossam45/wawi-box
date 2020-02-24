package com.jkaref.wawi.frontend;

import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

public class Template {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
