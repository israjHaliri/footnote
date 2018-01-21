package com.haliri.israj.appservice;

import com.haliri.israj.appservice.config.ClientInterceptor;
import org.apache.catalina.filters.RemoteAddrFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication(scanBasePackages = { "com.haliri.israj" })
public class AppService {

	public static void main(String[] args) {
		SpringApplication.run(AppService.class, args);
	}
}
