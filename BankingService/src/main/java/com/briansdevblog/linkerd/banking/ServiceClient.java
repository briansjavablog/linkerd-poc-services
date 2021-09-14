package com.briansdevblog.linkerd.banking;

import com.briansdevblog.linkerd.banking.model.CurrentAccount;
import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
@AllArgsConstructor
public class ServiceClient<T> {

    private RestTemplate restTemplate;

    public <T> List<T> callService(String uri){

        return restTemplate.exchange(uri, HttpMethod.GET,
                                   null,
                                    new ParameterizedTypeReference<List<T>>() {}).getBody();
    }
}
