package com.study.leetcode;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 注：当提交leetCode时，<K,V>需写明具体类型
 * @author sakura
 * @Create 2021-05-08 9:15
 */
public class LRUCache<K,V> extends LinkedHashMap<K,V>{
    // 缓存坑位
    private  int capacity;

    /**
     * accessOrder– the ordering mode - true for access-order, false for insertion-order
     * accessOrder = true 和 accessOrder = false 的情况
     * 当 accessOrder = true 时，每次使用 key 时（put 或者 get 时），都将 key 对应的数据移动到队尾（右边），表示最近经常使用
     * 当 accessOrder = false 时，key 的顺序为插入双向链表时的顺序
     *
     * @param capacity
     */
    public LRUCache(int capacity) {
        // 实际调父类哪个构造方法都可以，本次用LinkedHashMap(int initialCapacity,
        //                         float loadFactor,
        //                         boolean accessOrder)
        super(capacity,0.75F,true);
        this.capacity = capacity;
    }

    /**
     * 用于判断是否需要删除最近最久未使用的节点
     *
     * 重写removeEldestEntry逻辑：
     * 如果 LinkedHashMap 中存储的元素个数已经大于缓存容量 capacity，则返回 true，表示允许删除 EldestEntry；
     * 否则返回 false，表示无需删除 EldestEntry
     *
     * LinkedHashMap源码中removeEldestEntry(Map.Entry<K, V> eldest) 方法在 void afterNodeInsertion(boolean evict) 方法中被调用，
     * 只有当 boolean removeEldestEntry(Map.Entry<K, V> eldest) 方法返回 true 时，才能够删除 EldestEntry
      */
    @Override
    protected boolean removeEldestEntry(Map.Entry<K,V> eldest) {
        return super.size() > capacity;
    }

    // int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
    @Override
    public V get(Object key) {
        // return super.get(key)==null? -1 :super.get(key);
        return super.getOrDefault(key, (V)Integer.valueOf(-1));
    }

    public static void main(String[] args) {
        LRUCache lruCacheDemo = new LRUCache(3);

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
