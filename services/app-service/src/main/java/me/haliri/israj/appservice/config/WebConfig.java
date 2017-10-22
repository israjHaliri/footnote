package me.haliri.israj.appservice.config;

import me.haliri.israj.appservice.filter.RestFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by israjhaliri on 8/31/17.
 */

//uncoment this if using signature payload
//@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
    @Bean
    public FilterRegistrationBean httpRequestFilter() {
        FilterRegistrationBean bean = new FilterRegistrationBean(new RestFilter());
        bean.setOrder(0);
        return bean;
    }
}
