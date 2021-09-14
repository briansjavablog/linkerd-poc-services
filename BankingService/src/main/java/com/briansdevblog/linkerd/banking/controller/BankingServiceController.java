package com.briansdevblog.linkerd.banking.controller;

import com.briansdevblog.linkerd.banking.ServiceClient;
import com.briansdevblog.linkerd.banking.model.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
public class BankingServiceController {

    private RestTemplate restTemplate;
    private ServiceClient serviceClient;
    private UriConfig uriConfig;


    @GetMapping(path = "api/account-details/{customerNumber}")
    public CustomerAccounts getCustomerAccountDetails(@PathVariable("customerNumber") Integer customerNumber){

        log.info("retrieving account details or customerNumber [{}]", customerNumber);

        List<CurrentAccount> currentAccounts = serviceClient.callService(uriConfig.getCurrentAccountServiceUri() + "/" + customerNumber);
        log.info("retrieved [{}]", currentAccounts);

        List<SavingsAccount> savingsAccounts = serviceClient.callService(uriConfig.getSavingsAccountServiceUri() + "/" + customerNumber);
        log.info("retrieved [{}]", savingsAccounts);

        List<CreditCard> creditCards = serviceClient.callService(uriConfig.getCreditCardServiceUri() + "/" + customerNumber);
        log.info("retrieved [{}]", creditCards);

        CustomerAccounts customerAccounts = CustomerAccounts.builder()
                                                .currentAccounts(currentAccounts)
                                                .savingsAccounts(savingsAccounts)
                                                .creditCards(creditCards)
                                                .build();

        log.info("customerNumber [{}] has [{}]", customerNumber, customerAccounts);

        return customerAccounts;
    }

}