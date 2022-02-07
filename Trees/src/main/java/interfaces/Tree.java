package interfaces;

import java.util.Collection;

public interface Tree<E> {

    //CREATE

    public void insert(E element);

    //RETRIEVE

    public boolean contains(E element);

    public Collection<E> inOrder();

    public Collection<E> preOrder();

    public Collection<E> postOrder();

    public Collection<E> levelOrder();

    public int size();

    public boolean isEmpty();

    //UPDATE

    //DELETE

    public boolean remove(E element);
}
