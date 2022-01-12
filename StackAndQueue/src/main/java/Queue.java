import java.util.Iterator;

public class Queue<E> implements interfaces.Queue<E> {
    private Node<E> head;
    private Node<E> tail;
    private int size;

    public Queue() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    private static class Node<E> {
        private E element;
        private Node<E> next;

        public Node(E element) {
            this.element = element;
            this.next = null;
        }
    }

    // CREATE
    @Override
    public boolean offer(E element) {
        Node<E> node = new Node<>(element);
        if (isEmpty()) {
            this.head = node;
        } else {
            this.tail.next = node;
        }
        this.tail = node;
        this.size++;
        return true;

    }

    // RETRIEVE
    @Override
    public E peek() {
        if (this.isEmpty()) {
            throw new IllegalStateException();
        }
        return this.head.element;
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
    public boolean contains(Object o) {
        Node<E> node = this.head;

        return false;
    }

    @Override
    public Object[] toArray() {
        Object[] arr = new Object[this.size];

        return new Object[0];
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Node<E> node = head;

            @Override
            public boolean hasNext() {
                return this.node != null;
            }

            @Override
            public E next() {
                E element = this.node.element;
                this.node = this.node.next;
                return element;
            }
        };
    }
    // UPDATE


    // DELETE
    @Override
    public void clear() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    @Override
    public E poll() {
        if (this.isEmpty()) {
            throw new IllegalStateException();
        }
        E element = this.head.element;
        this.head = this.head.next;
        this.size--;

        return element;
    }
}
