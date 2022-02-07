package interfaces;

import java.util.Collection;

public interface SimpleTree<E> {
    //CREATE

    public boolean insert(E parent, E element);

    //RETRIEVE

    public boolean contains(E element);

    public Collection<E> BreathFirstSearch();

    public Collection<E> DepthFirstSearch();

    public int size();

    public boolean isEmpty();

    //UPDATE

    //DELETE

    public boolean remove(E element);
}
