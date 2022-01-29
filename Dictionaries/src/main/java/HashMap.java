import interfaces.Map;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class HashMap<K, V> implements Map<K, V> {
    private LinkedList<Entry<K, V>>[] buckets;
    private static final int INITIAL_CAPACITY = 16;
    private static final double LOAD_FACTOR = 0.8d;
    private int size;


    public HashMap() {
        this.buckets = new LinkedList[INITIAL_CAPACITY];
        this.size = 0;
    }

    public HashMap(int capacity) {
        this.buckets = new LinkedList[capacity];
        this.size = 0;
    }

    private static class Entry<K, V> {
        private final K key;
        private V value;
        Entry<K, V> next;

        private Entry(K key, V value, Entry<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;

        }
    }


    //CREATE

    @Override
    public void put(K key, V value) {
        growIfNeeded();
        Entry<K, V> entry = new Entry<>(key, value, null);
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
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    @Override
    public boolean containsValue(V value) {
        return false;
    }

    //UPDATE

    //DELETE

    @Override
    public boolean remove(K key) {
        List<Entry<K, V>> bucket = buckets[getHash(key) % getBucketSize()];


        this.size--;
        return true;
    }

    //HELPERS


    private int getBucketSize() {
        return this.buckets.length;
    }

    private int getHash(K key) {
        return key.hashCode();
    }

    private void growIfNeeded() {
        if (((double) this.size + 1) / this.buckets.length > LOAD_FACTOR) {
            this.grow();
        }
    }

    private void grow() {
        HashMap<K, V> newMap = new HashMap<>(this.buckets.length * 2);

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
