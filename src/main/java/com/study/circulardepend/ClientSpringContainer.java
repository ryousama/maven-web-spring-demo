package com.study.circulardepend;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author sakura
 * @Create 2021-05-06 14:20
 */
public class ClientSpringContainer {
    public static void main(String[] args) {
        ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
        A a=context.getBean("a",A.class);
        B b=context.getBean("b",B.class);
    }
}
