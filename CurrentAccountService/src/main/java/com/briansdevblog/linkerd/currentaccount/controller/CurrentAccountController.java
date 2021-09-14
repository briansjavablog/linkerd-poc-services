package com.briansdevblog.linkerd.currentaccount.controller;

import com.briansdevblog.linkerd.currentaccount.model.CurrentAccount;
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
public class CurrentAccountController {

    @GetMapping(path = "api/current-account/{customerNumber}")
    public List<CurrentAccount> getCurrentAccounts(@PathVariable("customerNumber") Integer customerNumber){

        // returns data for certain customer numbers, otherwise a non
        // 200 response - handy for triggering error scenarios

        if(customerNumber.equals(12345)){

            return Arrays.asList(new CurrentAccount(123456781L, "personal current account", 1800.00),
                                 new CurrentAccount(123456782L, "joined current account", 3200.00));
        }
        else {

            log.error("unknown customer number [{}]", customerNumber);
            throw new RuntimeException("unknown customer number " + customerNumber);
        }
    }
}