package com.study.circulardepend.setinjection;

/**
 * @author sakura
 * @Create 2021-05-06 10:52
 */
public class ClientSet {
    public static void main(String[] args) {
        ServiceAA a=new ServiceAA();
        ServiceBB b =new ServiceBB();
        b.setServiceAA(a);
        a.setServiceBB(b);
    }
}
