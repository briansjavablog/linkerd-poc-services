package com.briansdevblog.linkerd.banking.model;

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
