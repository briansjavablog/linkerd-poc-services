package com.briansdevblog.linkerd.banking.model;

import lombok.*;

@Setter
@Getter
@Builder
@ToString
@AllArgsConstructor
public class CreditCard {

    private Long cardNumber;
    private String cardName;
    private Double balance;
    private Double creditLimit;
    private Double apr;
}
