package com.briansdevblog.linkerd.creditcard.controller;

import com.briansdevblog.linkerd.creditcard.model.CreditCard;
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
public class CreditCardController {

    @GetMapping(path = "api/credit-card/{customerNumber}")
    public List<CreditCard> getCreditCards(@PathVariable("customerNumber") Integer customerNumber){

        // returns data for certain customer numbers, otherwise a non
        // 200 response - handy for triggering error scenarios

        if(customerNumber.equals(12345)){

            return Arrays.asList(new CreditCard(123456781L, "Brians Visa Card", 2200.00, 10000.0, 2.3),
                                 new CreditCard(123456782L, "Brians Mastercard", 800.00, 20000.0, 5.3));
        }
        else {

            log.error("unknown customer number [{}]", customerNumber);
            throw new RuntimeException("unknown customer number " + customerNumber);
        }
    }
}