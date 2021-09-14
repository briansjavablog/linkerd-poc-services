package com.briansdevblog.linkerd.banking.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class SavingsAccountList {

    private List<SavingsAccount> savingsAccounts;

    public SavingsAccountList() {
        savingsAccounts = new ArrayList<>();
    }
}