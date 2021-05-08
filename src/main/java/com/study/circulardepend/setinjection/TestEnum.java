package com.study.circulardepend.setinjection;

import com.study.circulardepend.A;

/**
 * @author sakura
 * @Create 2021-05-06 15:17
 */
enum TestEnum {
    ONE("A",1),
    TWO("B",2);

    private String name;
    private int index;

    // 构造方法
    private TestEnum(String name,int index) {
        this.name = name;
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public int getIndex() {
        return index;
    }


}
