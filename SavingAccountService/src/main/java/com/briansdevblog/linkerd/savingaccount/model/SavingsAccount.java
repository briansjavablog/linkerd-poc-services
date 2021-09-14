package com.briansdevblog.linkerd.savingaccount.model;

import lombok.*;

@Setter
@Getter
@Builder
@ToString
@AllArgsConstructor
public class SavingsAccount {

    private Long accountNumber;
    private String accountName;
    private Double balance;
}
