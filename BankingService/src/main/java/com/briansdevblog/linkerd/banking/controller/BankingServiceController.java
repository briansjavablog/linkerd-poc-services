package com.briansdevblog.linkerd.banking.controller;

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
    private UriConfig uriConfig;


    @GetMapping(path = "api/account-details/{customerNumber}")
    public CustomerAccounts getCustomerAccountDetails(@PathVariable("customerNumber") Integer customerNumber){

        // returns data for certain customer numbers, otherwise a non
        // 200 response - handy for triggering error scenarios

        List<CurrentAccount> currentAccounts =
                    restTemplate.exchange(uriConfig.getCurrentAccountServiceUri() + "/" + customerNumber,
                            HttpMethod.GET, null, new ParameterizedTypeReference<List<CurrentAccount>>() {
                            }).getBody();

        /*List<SavingsAccount> savingsAccounts =
                restTemplate.getForObject(uriConfig.getSavingsAccountServiceUri() + "/" + customerNumber, SavingsAccountList.class).getSavingsAccounts();

        List<CreditCard> creditCards =
                restTemplate.getForObject(uriConfig.getCreditCardServiceUri() + "/" + customerNumber, CreditCardList.class).getCreditCards();
*/
        CustomerAccounts customerAccounts = CustomerAccounts.builder()
                                                .currentAccounts(currentAccounts)
                                                .savingsAccounts(null/*savingsAccounts*/)
                                                .creditCards(null/*creditCards*/)
                                                .build();

        log.info("customerNumber [{}] has [{}]", customerNumber, customerAccounts);

        return customerAccounts;
    }

}