package LeetCode;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

class LRUCache {
    public static void main(String[] args) {
        LRUCache c = new LRUCache(2);
        // ["LRUCache","put","put","get","put","get","put","get","get","get"]
        // [[2],[1,1],[2,2],[1],[3,3],[2],[4,4],[1],[3],[4]]
        c.put(1,1);
        c.put(2,2);
        System.out.println(c.get(1));
        c.put(3,3);
        System.out.println(c.get(2));
        c.put(4,4);
        System.out.println(c.get(1));
        System.out.println(c.get(3));
        System.out.println(c.get(4));
    }

    HashMap<Integer, Integer> data;
    int size = 0;
    int lastUsed = -1;

    public LRUCache(int capacity) {
        data = new HashMap<>();
        size = 2;
    }

    public int get(int key) {
        if (data.get(key) == null)
            return -1;
        // lastUsed = key;
        return data.get(key);
    }

    public void put(int key, int value) {
        if (data.size() == size && data.get(key) == null)
            data.remove(lastUsed);
        data.put(key, value);
        lastUsed = key;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */


class LRUCache2 extends LinkedHashMap<Integer, Integer>{
    private int capacity;
    
    public LRUCache2(int capacity) {
        super(capacity, 0.75F, true);
        this.capacity = capacity;
    }

    public int get(int key) {
        return super.getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        super.put(key, value);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
        return size() > capacity; 
    }
}

