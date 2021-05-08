package com.study.circulardepend.setinjection;

import org.springframework.stereotype.Component;

/**
 * @author sakura
 * @Create 2021-05-06 10:52
 */
@Component
public class ServiceBB {
    private ServiceAA serviceAA;

    public void setServiceAA(ServiceAA serviceAA){
        this.serviceAA = serviceAA;
        System.out.println("B 里面设置了A");
    }
}
