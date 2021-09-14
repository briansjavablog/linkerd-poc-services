package com.briansdevblog.linkerd.banking.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
//@NoArgsConstructor
public class CurrentAccountList {

    private List<CurrentAccount> currentAccounts;

    public CurrentAccountList() {
        currentAccounts = new ArrayList<>();
    }
}