import java.util.Collection;
import java.util.Iterator;

public interface List<E> {
    //CREATE

    boolean add(E element);

    boolean addFirst(E element);

    boolean addLast(E element);


    //RETRIEVE

    E get(int index);

    int indexOf(E element);

    int lastIndexOf(E element);

    boolean isEmpty();

    Object[] toArray();

    Iterator<E> iterator();

    int size();

    boolean contains(Object o);


    //UPDATE

    E set(int index, E element);

    boolean replace(E oldElement, E newElement);

    boolean replaceAll(E oldElement, E newElement);

    boolean swap(int index1, int index2);


    //DELETE

    E remove(int index);

    boolean remove(Object element);

    void clear();

    boolean removeAll(E element);

}
