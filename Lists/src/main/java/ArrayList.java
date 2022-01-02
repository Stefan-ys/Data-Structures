import java.util.Iterator;

public class ArrayList<E> implements List<E> {
    private static final int DEFAULT_CAPACITY = 64;
    private Object[] elements;
    private int size;

    public ArrayList() {
        this.elements = new Object[DEFAULT_CAPACITY];
    }

    public ArrayList(int size) {
        this.elements = new Object[size];
    }

    @Override
    public boolean add(E element) {
        return false;
    }

    @Override
    public boolean addFirst(E element) {
        return false;
    }

    @Override
    public boolean addLast(E element) {
        return false;
    }

    @Override
    public E get(int index) {
        return null;
    }

    @Override
    public int indexOf(E element) {
        return 0;
    }

    @Override
    public int lastIndexOf(E element) {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public E set(int index, E element) {
        return null;
    }

    @Override
    public boolean replace(E oldElement, E newElement) {
        return false;
    }

    @Override
    public boolean replaceAll(E oldElement, E newElement) {
        return false;
    }

    @Override
    public boolean swap(int index1, int index2) {
        return false;
    }

    @Override
    public E remove(int index) {
        return null;
    }

    @Override
    public boolean remove(Object element) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean removeAll(E element) {
        return false;
    }
}
