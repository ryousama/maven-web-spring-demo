package com.study.circulardepend.constructorinjection;

import org.springframework.stereotype.Component;

/**
 * @author sakura
 * @Create 2021-05-06 10:45
 */
@Component
public class ServiceB {
    private ServiceA serviceA;

    public ServiceB(ServiceA serviceA){
        this.serviceA = serviceA;
    }
}
