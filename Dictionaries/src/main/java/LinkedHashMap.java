import interfaces.Map;

import java.util.Collection;

public class LinkedHashMap<K, V> implements Map<K, V> {
    @Override
    public void put(K key, V value) {

    }

    @Override
    public V get(K key) {
        return null;
    }

    @Override
    public Collection<K> keys() {
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        return false;
    }

    @Override
    public boolean containsValue(V value) {
        return false;
    }

    @Override
    public boolean remove(K key) {
        return false;
    }
}
