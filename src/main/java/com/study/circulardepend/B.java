package com.study.circulardepend;

/**
 * @author sakura
 * @Create 2021-05-06 13:55
 */
public class B {
    private A a;

    public A getA() {
        return a;
    }

    public void setA(A a) {
        this.a = a;
    }

    public B(){
        System.out.println("------B created success");
    }
}
