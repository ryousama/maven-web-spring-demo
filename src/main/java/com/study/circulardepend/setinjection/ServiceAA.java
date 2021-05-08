package com.study.circulardepend.setinjection;

import org.springframework.stereotype.Component;

/**
 * @author sakura
 * @Create 2021-05-06 10:51
 */
@Component
public class ServiceAA {
    private ServiceBB serviceBB;

    public void setServiceBB(ServiceBB serviceBB){
        this.serviceBB = serviceBB;
        System.out.println("A 里面设置了B");
    }
}
