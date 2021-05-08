package com.study.circulardepend.constructorinjection;

import org.springframework.stereotype.Component;

/**
 * @author sakura
 * @Create 2021-05-06 10:44
 */
@Component
public class ServiceA {
    private ServiceB serviceB;

    public ServiceA(ServiceB serviceB){
        this.serviceB = serviceB;
    }
}
