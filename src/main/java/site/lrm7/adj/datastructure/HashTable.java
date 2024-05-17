package site.lrm7.adj.datastructure;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class HashTable {
    static class Entry {
        int hash;
        Object key;
        Object value;
        Entry next;

        public Entry(int hash, Object key, Object value) {
            this.hash = hash;
            this.key = key;
            this.value = value;
        }
    }

    Entry[] table = new Entry[16];
    int size = 0;
    float loadFactor = 0.75f;
    int threshold = (int) (loadFactor * table.length);

    /*
        求模运算替换为位运算
            - 前提： 数组长度是2的n次方
            - hash % 数组长度 等价于 hash & （数组长度）
     */

    // 根据 hash 获取 value
    Object get(int hash, Object key) {
        int idx = hash & (table.length - 1);
        Entry p = table[idx];
        if (p == null) {
            return null;
        }
        while (p != null) {
            if (p.key.equals(key)) {
                return p;
            }
            p = p.next;
        }
        return null;
    }

    void put(int hash, Object key, Object value) {
        if (size > threshold) {
            resize();
        }
        int idx = hash & (table.length - 1);
        Entry p = table[idx];
        if (p == null) {
            table[idx] = new Entry(hash, key, value);
            size++;
            return;
        }
        while (true) {
            if (p.key.equals(key)) {
                p.value = value;
                return;
            }
            if (p.next == null) {
                p.next = new Entry(hash, key, value);
                size++;
                return;
            }
            p = p.next;
        }
    }

    private void resize() {
        Entry[] newTable = new Entry[table.length << 1];
        for (int i = 0; i < table.length - 1; i++) {
            Entry p = table[i];
            if (p != null) {
                Entry a = null;
                Entry b = null;
                Entry aHead = null;
                Entry bHead = null;
                while (p != null) {
                    if ((p.hash & table.length) == 0) {
                        if (a != null) {
                            a.next = p;
                            a = a.next;
                        }else {
                            aHead = p;
                        }
                        a = p;
                    } else {
                        if (b != null) {
                            b.next = p;
                        }else {
                            bHead = p;
                        }
                        b = p;
                    }
                    p = p.next;
                }
                if (a != null) {
                    a.next = null;
                    newTable[i] = aHead;
                }
                if (b != null) {
                    b.next = null;
                    newTable[i + table.length] = bHead;
                }
            }
        }
        table = newTable;
        threshold = (int) (table.length * loadFactor);
    }

    Object remove(int hash, Object key) {
        int idx = hash & (table.length - 1);
        Entry p = table[idx];
        Entry parent = null;
        while (p != null) {
            if (p.key.equals(key)) {
                if (parent == null) {
                    table[idx] = p.next;
                } else {
                    parent.next = null;
                }
                size--;
                return p.value;
            }
            parent = p;
            p = p.next;
        }

        return null;
    }

    Object remove(Object key) {
        return remove(getHash(key), key);
    }

    void put(Object key, Object value) {
        put (getHash(key), key, value);
    }

    Object get(Object key) {
        return get(getHash(key), key);
    }

    private int getHash(Object key) {
        if (key instanceof String k) {
            return Hashing.murmur3_32_fixed().hashString(k, StandardCharsets.UTF_8).asInt();
        }
        return key.hashCode();
    }

    public void print() {
        int[] sums = new int[table.length];
        for (int i = 0; i < table.length; i++) {
            Entry p = table[i];
            while (p!=null) {
                sums[i] ++;
                p = p.next;
            }
        }
        Map<Integer, Long> collect = Arrays.stream(sums).boxed().collect(Collectors.groupingBy(e -> e, Collectors.counting()));
        System.out.println(collect);

    }

    public static void main(String[] args) {
        HashTable table1 = new HashTable();
//        for (int i = 0; i < 200000; i++) {
//            Object obj = new Object();
//            table1.put(obj, obj);
//        }

        table1.put(2,2);
        table1.put(524290,2);
        table1.print();
    }
}
