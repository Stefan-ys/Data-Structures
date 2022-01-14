package interfaces;

import java.util.Iterator;

public interface Deque<E> {


    // CREATE


    boolean offer(E element);

    boolean push(E element);

    boolean addFirst(E element);

    boolean addLast(E element);

    //RETRIEVE

    E peek();

    E peekFirst();

    E peekLast();

    int size();

    boolean isEmpty();

    Iterator<E> iterator();

    //UPDATE

    //DELETE

    E poll();

    E pop();

    E removeFirst();

    E removeLast();

    void clear();
}
