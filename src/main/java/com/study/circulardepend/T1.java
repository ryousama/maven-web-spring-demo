package com.study.circulardepend;

/**
 * @author sakura
 * @Create 2021-05-06 10:07
 */
public class T1 {
    class A{
        B b;
    }

    class B{
        C c;
    }
    class C{
        A a;
    }
}
