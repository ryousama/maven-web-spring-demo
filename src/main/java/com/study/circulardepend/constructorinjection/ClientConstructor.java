package com.study.circulardepend.constructorinjection;

/**
 * @author sakura
 * @Create 2021-05-06 10:47
 */
public class ClientConstructor {
    public static void main(String[] args) {
        // 如俄罗斯套娃，无法创建
        //new ServiceA(new ServiceB(new ServiceA(new ServiceB())));
    }
}
