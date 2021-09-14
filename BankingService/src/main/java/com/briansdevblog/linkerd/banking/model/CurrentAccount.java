package com.briansdevblog.linkerd.banking.model;

import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class CurrentAccount {

    private Long accountNumber;
    private String accountName;
    private Double balance;
}
