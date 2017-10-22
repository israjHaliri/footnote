/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.haliri.israj.appcore.config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 *
 * @author IsrajHaliri
 */
@Component
public class MainDatasourceConfig {

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.driverClassName}")
    private String driver;

    @Value("${spring.datasource.validation-query}")
    private String validationQuery;


    @Bean
    public DataSource dataSource() {
        DataSource ds = new DataSource();
        ds.setUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);
        ds.setDriverClassName(driver);
        ds.setValidationQuery(validationQuery);
        ds.setInitialSize(10);
        ds.setMinIdle(10);
        ds.setMaxIdle(20);
        ds.setMaxActive(20);
        ds.setMaxWait(40000);
        ds.setTestOnBorrow(true);
        return ds;
    }
    
}
