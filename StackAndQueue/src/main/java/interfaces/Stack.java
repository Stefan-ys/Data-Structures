package interfaces;

import java.util.Iterator;

public interface Stack<E> {

    // CREATE
    boolean push(E element);

    // RETRIEVE
    int size();

    E peek();

    boolean isEmpty();

    boolean contains(Object o);

    Object[] toArray();

    Iterator<E> iterator();

    // UPDATE
    // DELETE
    E pop();

    void clear();
}
