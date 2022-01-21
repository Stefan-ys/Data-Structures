import interfaces.Deque;

import java.util.Iterator;

public class ArrayDeque<E> implements Deque<E> {
    private final int CAPACITY = 64;
    private Object[] elements;
    private int head;
    private int tail;
    private int size;

    public ArrayDeque() {
        this.elements = new Object[CAPACITY];
        this.head = elements.length / 2;
        this.tail = elements.length / 2;
        this.size = 0;
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
        if (!this.isEmpty()) {
            this.head--;
        }
        setCapacity();
        this.elements[this.head] = element;
        this.size++;
        return true;
    }


    @Override
    public boolean addLast(E element) {
        if (!this.isEmpty()) {
            this.tail++;
        }
        setCapacity();
        this.elements[this.tail] = element;
        this.size++;
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
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int index = head;

            @Override
            public boolean hasNext() {
                return this.index <= tail;
            }

            @Override
            public E next() {
                E element = (E) elements[index];
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
        this.size--;
        return element;

    }

    @Override
    public E removeLast() {
        if (this.isEmpty()) {
            throw new IllegalStateException();
        }
        E element = (E) this.elements[this.tail];
        this.tail--;
        this.size--;
        return element;
    }

    @Override
    public void clear() {
        this.elements = new Object[CAPACITY];
        this.head = elements.length / 2;
        this.tail = elements.length / 2;
        this.size = 0;
    }


    // HELPERS


    private void setCapacity() {
        if (this.head <= 1 || this.tail >= this.elements.length - 2) {

            Object[] newArray = new Object[this.elements.length * 2];

            int newHead = this.size / 2;

            if (this.size >= 0) System.arraycopy(this.elements, this.head, newArray, newHead, this.size);

            this.tail = newHead + this.size;
            this.head = newHead;
            this.elements = newArray;
        }
    }
}
