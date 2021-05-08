package com.study.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author sakura
 * @Create 2021-05-08 12:07
 */
public class LRUCacheLeetCode {

    static final class Node {
        Integer key;
        Integer value;
        Node prev;
        Node next;

        public Node(){
            this.prev = this.next = null;
        }

        public Node(Integer key, Integer value) {
            this.key = key;
            this.value = value;
            this.prev = this.next = null;
        }
    }

    class DoubleLinkedList {
        Node head;
        Node tail;

        public DoubleLinkedList() {
            head = new Node();
            tail = new Node();
            head.next = tail;
            tail.prev = head;
        }

        // 3.添加到头,类似AQS，头节点head实际是个空节点，所以node其实是添加到head节点之后
        public void addHead(Node node) {
            node.next = head.next;
            node.prev = head;
            head.next.prev = node;
            head.next = node;
        }

        // 4.删除节点
        public void removeNode(Node node) {
//            head.next = node.next;
//            node.next.prev = head;
            node.next.prev = node.prev;
            node.prev.next = node.next;
            node.prev = node.next =null;
        }

        // 获得最后一个节点
        public Node getLast() {
            return tail.prev;
        }
    }

    private int cacheSize;
    public Map<Integer, Node> map;
    public DoubleLinkedList doubleLinkedList;

    public LRUCacheLeetCode(int cacheSize) {
        this.cacheSize = cacheSize;//坑位
        map = new HashMap<>();//查找
        doubleLinkedList = new DoubleLinkedList();
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }

        Node node = map.get(key);
        doubleLinkedList.removeNode(node);
        doubleLinkedList.addHead(node);

        return node.value;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {  //update
            Node node = map.get(key);
            node.value = value;
            map.put(key, node);

            doubleLinkedList.removeNode(node);
            doubleLinkedList.addHead(node);
        } else {
            if (map.size() == cacheSize)  //坑位满了
            {
                Node lastNode = doubleLinkedList.getLast();
                map.remove(lastNode.key);
                doubleLinkedList.removeNode(lastNode);
            }

            //新增一个
            Node newNode = new Node(key, value);
            map.put(key, newNode);
            doubleLinkedList.addHead(newNode);

        }
    }

    public static void main(String[] args) {
        LRUCacheLeetCode lruCacheDemo = new LRUCacheLeetCode(3);

        lruCacheDemo.put(1, 1);
        lruCacheDemo.put(2, 2);
        lruCacheDemo.put(3, 3);
        System.out.println(lruCacheDemo.map.keySet());

        System.out.println(lruCacheDemo.get(5));
        lruCacheDemo.put(4, 4);
        System.out.println(lruCacheDemo.map.keySet());
        System.out.println(lruCacheDemo.get(3));
        lruCacheDemo.put(3, 1);
        System.out.println(lruCacheDemo.map.keySet());
        System.out.println(lruCacheDemo.get(3));
        lruCacheDemo.put(3, 1);
        System.out.println(lruCacheDemo.map.keySet());
        lruCacheDemo.put(3, 1);
        System.out.println(lruCacheDemo.map.keySet());
        lruCacheDemo.put(5, 1);
        System.out.println(lruCacheDemo.map.keySet());
    }
}
