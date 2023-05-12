package lv.demo.leetcode;

import java.util.HashMap;
import java.util.Map;

public class Demo146 {

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(2);
        lruCache.put(1, 1);
        lruCache.put(2, 2);
        lruCache.get(1);
        lruCache.put(3, 3);
        lruCache.get(2);
        lruCache.put(4, 4);
        System.out.println(lruCache.get(1));
        lruCache.get(3);
        lruCache.get(4);
    }

    private static class LRUCache {

        private final int capacity;
        private int count;
        private final Node head = new Node();
        private final Node tail = new Node();
        private final Map<Integer, Node> cache = new HashMap<>();

        public LRUCache(int capacity) {
            this.capacity = capacity;
            head.next = tail;
            tail.prev = head;
        }

        public int get(int key) {
            Node node = cache.get(key);
            if (node == null) {
                return -1;
            }
            //
            refreshNode(node);

            //
            return node.value;
        }

        public void put(int key, int value) {
            Node node = cache.get(key);
            if (node == null) {
                node = new Node();
                node.key = key;
                node.value = value;

                cache.put(key, node);
                count++;
            } else {
                node.value = value;
            }
            //
            refreshNode(node);
            //
            if (count > capacity) {
                cache.remove(tail.prev.key);
                Node temp = tail.prev.prev;
                tail.prev.prev.next = tail;
                tail.prev = temp;
                count--;
            }
        }

        private void refreshNode(Node node) {
            if (node.prev != null && node.next != null) {
                node.prev.next = node.next;
                node.next.prev = node.prev;
            }

            node.next = head.next;
            head.next.prev = node;
            node.prev = head;
            head.next = node;
        }
    }

    private static class Node {

        private int key;
        private int value;
        private Node prev;
        private Node next;
    }
}
