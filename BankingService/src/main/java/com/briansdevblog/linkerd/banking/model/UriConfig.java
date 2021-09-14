package com.briansdevblog.linkerd.banking.model;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Builder
@ToString
public class UriConfig {

    private String currentAccountServiceUri;
    private String savingsAccountServiceUri;
    private String creditCardServiceUri;
}