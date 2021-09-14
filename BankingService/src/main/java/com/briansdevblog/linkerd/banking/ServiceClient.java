package com.briansdevblog.linkerd.banking;

import com.briansdevblog.linkerd.banking.model.CurrentAccount;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;

import java.util.List;

public class ServiceClient<T> {


    public List<T> test(){

        List<CurrentAccount> currentAccounts =
                restTemplate.exchange(uriConfig.getCurrentAccountServiceUri() + "/" + customerNumber,
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<CurrentAccount>>() {
                        }).getBody();
    }
}
