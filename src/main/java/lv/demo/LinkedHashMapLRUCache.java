package lv.demo;

import java.util.LinkedHashMap;
import java.util.Map;

public class LinkedHashMapLRUCache<K, V> extends LinkedHashMap<K, V> {

    private final int cacheSize;

    public LinkedHashMapLRUCache(int cacheSize) {
        super(cacheSize, 0.75f, true);
        this.cacheSize = cacheSize;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > cacheSize;
    }
}
