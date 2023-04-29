package com.example.uet_tty.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfig {

    @Bean
    public FilterRegistrationBean<Filter> loggingFilter(){
        FilterRegistrationBean<Filter> registrationBean
                = new FilterRegistrationBean<>();

        registrationBean.setFilter(new Filter());
        registrationBean.addUrlPatterns("/expert/*","/student/*","/homepage","/freetime/*","/meeting/*");

        return registrationBean;
    }
}