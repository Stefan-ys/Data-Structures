
import java.util.Arrays;
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

    //CREATE

    @Override
    public boolean add(E element) {
        grow();
        this.elements[this.size++] = element;

        return true;
    }

    @Override
    public boolean add(int index, E element) {
        if (index < 0 || index > this.size) {
            throw new IllegalStateException();
        }
        grow();

        shiftRight(index);
        elements[index] = element;


        return true;
    }

    @Override
    public boolean addFirst(E element) {
        return add(0, element);
    }

    @Override
    public boolean addLast(E element) {
        return add(element);
    }

    //RETRIEVE

    @Override
    public E get(int index) {
        checkIndex(index);

        return (E) this.elements[index];
    }

    @Override
    public int indexOf(E element) {
        for (int i = 0; i < this.size; i++) {
            if (this.elements[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(E element) {
        for (int i = this.size - 1; i >= 0; i--) {
            if (this.elements[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(this.elements, this.size);
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return this.index < size;
            }

            @Override
            public E next() {
                return (E) elements[index++];
            }
        };
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < this.size; i++) {
            if (this.elements[i].equals(o)) {
                return true;
            }
        }
        return false;
    }

    //UPDATE

    @Override
    public E set(int index, E element) {
        checkIndex(index);
        E e = (E) this.elements[index];
        this.elements[index] = element;
        return e;
    }

    @Override
    public boolean replace(E oldElement, E newElement) {
        for (int i = 0; i < this.size; i++) {
            if (this.elements[i].equals(oldElement)) {
                this.elements[i] = newElement;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean replaceAll(E oldElement, E newElement) {
        boolean flag = false;
        for (int i = 0; i < this.size; i++) {
            if (this.elements[i].equals(oldElement)) {
                this.elements[i] = newElement;
                flag = true;
            }
        }
        return flag;
    }

    //DELETE

    @Override
    public E remove(int index) {
        checkIndex(index);
        E element = (E) this.elements[index];
        shiftLeft(index);

        shrink();
        return element;
    }

    @Override
    public boolean remove(Object element) {
        for (int i = 0; i < this.size; i++) {
            if (elements[i].equals(element)) {
                shiftLeft(i);
                shrink();
                return true;
            }
        }
        return false;
    }

    @Override
    public E removeFirst() {
        if (isEmpty()) {
            throw new IllegalStateException();
        }
        E element = (E) this.elements[0];
        shiftLeft(0);
        return null;
    }

    @Override
    public E removeLast() {
        if (isEmpty()) {
            throw new IllegalStateException();
        }

        E element = (E) this.elements[this.size - 1];
        shiftLeft(this.size - 1);
        return element;
    }

    @Override
    public void clear() {
        this.elements = new Object[DEFAULT_CAPACITY];
        this.size = 0;

    }

    @Override
    public boolean removeAll(E element) {
        boolean flag = false;
        for (int i = 0; i < this.size; i++) {
            if (this.elements[i].equals(element)) {
                shiftLeft(i);
                flag = true;
            }
        }
        shrink();
        return flag;
    }

    //HELPERS
    private void grow() {
        if (this.size == this.elements.length) {
            this.elements = Arrays.copyOf(this.elements, this.elements.length * 2);
        }
    }

    private void shrink() {
        if (this.size <= this.elements.length / 4) {
            this.elements = Arrays.copyOf(this.elements, this.elements.length / 2);
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException("IndexOutBounds");
        }
    }

    private void shiftRight(int index) {
        System.arraycopy(elements, index, elements, index + 1, this.size - index);
        this.size++;
    }

    private void shiftLeft(int index) {
        System.arraycopy(this.elements, index + 1, this.elements, index, this.size - index);
        this.size--;
    }


}
