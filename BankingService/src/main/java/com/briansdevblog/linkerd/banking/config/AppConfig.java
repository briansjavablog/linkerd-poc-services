package com.briansdevblog.linkerd.banking.config;

import com.briansdevblog.linkerd.banking.model.UriConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    @Bean
    public RestTemplate restTemplate(){

        return new RestTemplate();
    }

    @Bean
    public UriConfig uriConfig(@Value("${currentAccountServiceUri}") String currentAccountServiceUri,
                               @Value("${savingsAccountServiceUri}") String savingsAccountServiceUri,
                               @Value("${creditCardServiceUri}") String creditCardServiceUri){

        return UriConfig.builder().currentAccountServiceUri(currentAccountServiceUri)
                                  .savingsAccountServiceUri(savingsAccountServiceUri)
                                  .creditCardServiceUri(creditCardServiceUri)
                                  .build();
    }
}
