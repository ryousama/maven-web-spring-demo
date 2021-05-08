package com.study.circulardepend;

/**
 * @author sakura
 * @Create 2021-05-06 13:55
 */
public class A {
    private B b;

    public B getB() {
        return b;
    }

    public void setB(B b) {
        this.b = b;
    }

    public A(){
        System.out.println("-----A created success");
    }
}
