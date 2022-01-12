package interfaces;

import java.util.Iterator;

public interface Queue<E> {

    //CREATE

    boolean offer(E element);

    //RETRIEVE

    E peek();

    int size();

    boolean isEmpty();

    boolean contains(Object o);

    Object[] toArray();

    Iterator<E> iterator();

    //UPDATE

    //DELETE

    void clear();

    E poll();

}
