package com.briansdevblog.linkerd.currentaccount.model;

import lombok.*;

@Setter
@Getter
@Builder
@ToString
@AllArgsConstructor
public class CurrentAccount {

    private Long accountNumber;
    private String accountName;
    private Double balance;
}
