import interfaces.Deque;

import java.util.Iterator;

public class ArrayDeque<E> implements Deque<E> {
    private final int CAPACITY = 64;
    private Object[] elements;
    private int head;
    private int tail;

    public ArrayDeque() {
        this.elements = new Object[CAPACITY];
        this.head = elements.length / 2;
        this.tail = elements.length / 2;
    }

    // CREATE

    @Override
    public boolean offer(E element) {
        return addLast(element);
    }

    @Override
    public boolean push(E element) {
        return addFirst(element);
    }

    @Override
    public boolean addFirst(E element) {
        setCapacity();
        this.elements[--this.head] = element;
        return true;
    }


    @Override
    public boolean addLast(E element) {
        setCapacity();
        this.elements[this.tail++] = element;
        return true;
    }


    // RETRIEVE

    @Override
    public E peek() {
        if (this.isEmpty()) {
            throw new IllegalStateException();
        }
        return (E) this.elements[this.head];
    }

    @Override
    public E peekFirst() {
        return peek();
    }

    @Override
    public E peekLast() {
        if (this.isEmpty()) {
            throw new IllegalStateException();
        }
        return (E) this.elements[this.tail];
    }

    @Override
    public int size() {
        return this.tail - this.head;
    }

    @Override
    public boolean isEmpty() {
        return this.head == this.tail;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int index = 0;

            @Override
            public boolean hasNext() {
                return this.index < size();
            }

            @Override
            public E next() {
                E element = (E) elements[head + index];
                index++;
                return element;
            }
        };
    }

    // UPDATE

    // DELETE

    @Override
    public E pop() {
        return removeFirst();
    }

    @Override
    public E poll() {
        return removeLast();
    }

    @Override
    public E removeFirst() {
        if (this.isEmpty()) {
            throw new IllegalStateException();
        }
        E element = (E) this.elements[this.head];
        this.head++;
        return element;

    }

    @Override
    public E removeLast() {
        if (this.isEmpty()) {
            throw new IllegalStateException();
        }
        E element = (E) this.elements[this.tail];
        this.tail--;
        return element;
    }

    @Override
    public void clear() {
        this.elements = new Object[CAPACITY];
        this.head = elements.length / 2;
        this.tail = elements.length / 2;
    }


    // HELPERS


    private void setCapacity() {
        if (this.head == 1 || this.tail == this.elements.length - 2) {
            Object[] newArray = new Object[this.elements.length * 2];
            int newHead = newArray.length / 2 - this.size() / 2;

            if (this.size() >= 0) System.arraycopy(this.elements, this.head, newArray, newHead, this.size());
            this.tail = newHead + this.size();
            this.head = newHead;
            this.elements = newArray;
        }
    }
}
