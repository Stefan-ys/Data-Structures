import org.w3c.dom.Node;

import java.util.Iterator;

public class Stack<E> implements interfaces.Stack<E> {
    private Node<E> tail;
    private int size;

    public Stack() {
        this.tail = null;
        this.size = 0;
    }

    private static class Node<E> {
        private E element;
        private Node<E> prev;

        public Node(E element) {
            this.element = element;
        }
    }


    // CREATE
    @Override
    public boolean push(E element) {
        Node<E> node = new Node<>(element);
        node.prev = this.tail;
        this.tail = node;
        this.size++;
        return true;
    }

    // RETRIEVE
    @Override
    public int size() {
        return this.size;
    }

    @Override
    public E peek() {
        if (this.isEmpty()) {
            throw new IllegalStateException();
        }
        return this.tail.element;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public boolean contains(Object o) {
        if (this.isEmpty()) {
            throw new IllegalStateException();
        }

        Node<E> node = this.tail;
        while (node.prev != null) {
            if (node.element.equals(o)) {
                return true;
            }
            node = node.prev;
        }
        return true;
    }

    @Override
    public Object[] toArray() {
        Object[] arr = new Object[this.size];
        Node<E> node = this.tail;
        for (int i = 0; i < this.size; i++) {
            arr[i] = node.element;
            node = node.prev;
        }
        return arr;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Stack.Node<E> node = tail;

            @Override
            public boolean hasNext() {
                return this.node != null;
            }

            @Override
            public E next() {
                E element = this.node.element;
                this.node = node.prev;
                return element;
            }
        };

    }

    // UPDATE
    // DELETE 
    public E pop() {
        if (this.isEmpty()) {
            throw new IllegalStateException();
        }
        E element = this.tail.element;
        if (this.size == 1) {
            this.tail = null;
        } else {
            this.tail = this.tail.prev;
        }
        this.size--;
        return element;

    }

    public void clear() {
        this.tail = null;
        this.size = 0;
    }
}
