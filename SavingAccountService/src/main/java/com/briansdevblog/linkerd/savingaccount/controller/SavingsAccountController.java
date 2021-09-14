package com.briansdevblog.linkerd.savingaccount.controller;

import com.briansdevblog.linkerd.savingaccount.model.SavingsAccount;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
public class SavingsAccountController {

    @GetMapping(path = "api/savings-account/{customerNumber}")
    public List<SavingsAccount> getSavingsAccounts(@PathVariable("customerNumber") Integer customerNumber){

        // returns data for certain customer numbers, otherwise a non
        // 200 response - handy for triggering error scenarios

        if(customerNumber.equals(12345)){

            return Arrays.asList(new SavingsAccount(123456781L, "holiday savings", 5800.00),
                                 new SavingsAccount(123456782L, "retirement savings", 280000.00),
                                 new SavingsAccount(123456783L, "car savings", 25000.00));
        }
        else if(customerNumber.equals(123456)){

            return Arrays.asList(new SavingsAccount(123456785L, "holiday savings", 9500.00),
                                 new SavingsAccount(123456786L, "retirement savings", 500000.00));
        }
        else {

            log.error("unknown customer number [{}]", customerNumber);
            throw new RuntimeException("unknown customer number " + customerNumber);
        }
    }
}