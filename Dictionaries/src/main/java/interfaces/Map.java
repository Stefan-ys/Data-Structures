package interfaces;

import java.util.Collection;

public interface Map<K, V> {

    //CREATE
    void put(K key, V value);

    //RETRIEVE
    V get(K key);

    Collection<K> keys();

    boolean containsKey(K key);

    boolean containsValue(V value);

    //UPDATE

    //DELETE
    boolean remove(K key);
}
