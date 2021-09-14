package com.briansdevblog.linkerd.banking.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class CreditCardList {

    private List<CreditCard> creditCards;

    public CreditCardList() {
        creditCards = new ArrayList<>();
    }
}