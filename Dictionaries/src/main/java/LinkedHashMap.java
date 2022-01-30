import interfaces.Map;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class LinkedHashMap<K, V> implements Map<K, V> {
    private LinkedList<Entry<K, V>>[] buckets;
    private static final int INITIAL_CAPACITY = 16;
    private static final double LOAD_FACTOR = 0.8d;
    private int size;
    private Entry<K, V> head;
    private Entry<K, V> tail;


    public LinkedHashMap() {
        this.buckets = new LinkedList[INITIAL_CAPACITY];
        this.size = 0;
    }

    public LinkedHashMap(int capacity) {
        this.buckets = new LinkedList[capacity];
        this.size = 0;
    }

    private static class Entry<K, V> {
        private final K key;
        private V value;
        private Entry<K, V> next;
        private Entry<K, V> prev;

        private Entry(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
            this.prev = null;

        }
    }


    //CREATE

    @Override
    public void put(K key, V value) {
        growIfNeeded();
        Entry<K, V> entry = new Entry<>(key, value);
        if (this.isEmpty()) {
            this.head = entry;
        } else if (this.size == 1) {
            this.head.next = entry;
        } else {
            this.tail.next = entry;
        }
        this.tail = entry;

        int bucket = getHash(key) % getBucketSize();
        if (this.buckets[bucket] == null) {
            this.buckets[bucket] = new LinkedList<>();
            this.buckets[bucket].add(entry);
        } else {
            List<Entry<K, V>> list = this.buckets[bucket];
            for (Entry<K, V> current : list) {
                if (current.key.equals(entry.key)) {
                    current.value = entry.value;
                    return;
                }
            }
            list.add(entry);
        }
        this.size++;
    }

    //RETRIEVE

    @Override
    public V get(K key) {
        List<Entry<K, V>> list = buckets[getHash(key) % getBucketSize()];

        for (Entry<K, V> current : list) {
            if (current.key.equals(key)) {
                return current.value;
            }
        }
        return null;
    }


    @Override
    public Collection<K> keys() {
        List<K> keys = new ArrayList<>();

        Entry<K, V> entry = this.head;

        while (entry != null) {
            keys.add(entry.key);
            entry = entry.next;
        }
        return keys;
    }

    @Override
    public Collection<V> values() {
        List<V> values = new ArrayList<>();

        Entry<K, V> entry = this.head;

        while (entry != null) {
            values.add(entry.value);
            entry = entry.next;
        }
        return values;
    }

    @Override
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    @Override
    public boolean containsValue(V value) {
        Entry<K, V> entry = this.head;

        while (entry != null) {
            if (entry.value.equals(value)) {
                return true;
            }
            entry = entry.next;
        }
        return false;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public int size() {
        return this.size;
    }

    //UPDATE

    //DELETE

    @Override
    public boolean remove(K key) {
        List<Entry<K, V>> list = buckets[getHash(key) % getBucketSize()];
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).key.equals(key)) {
                list.remove(i);
                this.size--;
                Entry<K, V> entry = this.head;
                int index = 0;
                while (entry != null) {
                    if (entry.key.equals(key)) {
                        if (index == 0) {
                            this.head = this.head.next;
                            if (this.size == 1) {
                                this.tail = this.head;
                            }
                        } else if (index == this.size - 1) {
                            this.tail = this.tail.prev;
                        } else {
                            entry.prev.next = entry.next;
                        }
                        return true;
                    }
                    index++;
                    entry = entry.next;
                }
                return true;
            }
        }

        return false;
    }

    @Override
    public void clear() {
        this.buckets = new LinkedList[INITIAL_CAPACITY];
        this.size = 0;
        this.head = null;
        this.tail = null;
    }

    //HELPERS


    private int getBucketSize() {
        return this.buckets.length;
    }

    private int getHash(K key) {
        return Math.abs(key.hashCode());
    }

    private void growIfNeeded() {
        if (((double) this.size + 1) / this.buckets.length > LOAD_FACTOR) {
            this.grow();
        }
    }

    private void grow() {
        LinkedHashMap<K, V> newMap = new LinkedHashMap<>(this.buckets.length * 2);

        for (List<Entry<K, V>> list : this.buckets) {
            if (list != null) {
                for (Entry<K, V> entry : list) {
                    newMap.put(entry.key, entry.value);
                }
            }
        }
        this.buckets = newMap.buckets;
    }
}
