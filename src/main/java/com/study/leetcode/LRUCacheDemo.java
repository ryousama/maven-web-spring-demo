package com.study.leetcode;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 提交leetCode版本
 * @author sakura
 * @Create 2021-05-08 10:20
 */
public class LRUCacheDemo extends LinkedHashMap<Integer,Integer> {
    private  int capacity;

    public LRUCacheDemo(int capacity) {
        super(capacity,0.75F,true);
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer,Integer> eldest) {
        return super.size() > capacity;
    }

    public int get(int key) {
        return super.get(key)==null? -1 :super.get(key);
    }

    public static void main(String[] args) {
        LRUCacheDemo lruCacheDemo = new LRUCacheDemo(3);

        lruCacheDemo.put(1, 1);
        lruCacheDemo.put(2, 2);
        lruCacheDemo.put(3, 3);
        System.out.println(lruCacheDemo.keySet());

        System.out.println(lruCacheDemo.get(5));
        lruCacheDemo.put(4, 4);
        System.out.println(lruCacheDemo.keySet());

        lruCacheDemo.put(3, 3);
        System.out.println(lruCacheDemo.keySet());
        lruCacheDemo.put(3, 3);
        System.out.println(lruCacheDemo.keySet());
        lruCacheDemo.put(3, 3);
        System.out.println(lruCacheDemo.keySet());
        lruCacheDemo.put(5, 3);

    }
}
