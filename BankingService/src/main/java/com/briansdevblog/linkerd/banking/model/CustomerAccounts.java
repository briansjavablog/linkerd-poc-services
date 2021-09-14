package com.briansdevblog.linkerd.banking.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@Builder
@ToString
public class CustomerAccounts {

    private List<CurrentAccount> currentAccounts;
    private List<SavingsAccount> savingsAccounts;
    private List<CreditCard> creditCards;
}
