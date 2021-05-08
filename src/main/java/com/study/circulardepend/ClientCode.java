package com.study.circulardepend;

/**
 * @author sakura
 * @Create 2021-05-06 13:56
 */
public class ClientCode {
    public static void main(String[] args) {
        A a= new A();
        B b=new B();

        b.setA(a);
        a.setB(b);
    }
}
