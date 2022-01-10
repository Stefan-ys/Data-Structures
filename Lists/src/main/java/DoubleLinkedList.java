import java.util.Iterator;

public class DoubleLinkedList<E> implements List<E> {
    private Node<E> head;
    private Node<E> tail;
    private int size;

    public DoubleLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    private static class Node<E> {
        private E element;
        private Node<E> next;
        private Node<E> prev;

        public Node(E element) {
            this.element = element;
        }
    }

    //CREATE

    @Override
    public boolean add(E element) {
        return addLast(element);
    }

    @Override
    public boolean add(int index, E element) {
        if (index < 0 || index > this.size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            return addFirst(element);
        }
        if (index == this.size) {
            return addLast(element);
        }

        Node<E> node;
        if (index < this.size / 2) {
            node = this.head;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
        } else {
            node = this.tail;
            for (int j = 0; j < this.size - 1 - index; j++) {
                node = node.prev;
            }
        }
        Node<E> newNode = new Node<>(element);
        Node<E> tmp = node.prev;

        tmp.next = newNode;
        newNode.prev = tmp;
        newNode.next = node;
        node.prev = newNode;

        this.size++;
        return true;
    }

    @Override
    public boolean addFirst(E element) {
        Node<E> newNode = new Node<>(element);

        if (this.size == 0) {
            this.head = newNode;
            this.tail = newNode;
        }
        newNode.next = this.head;
        this.head.prev = newNode;
        this.head = newNode;

        if (this.size == 1) {
            this.tail = this.head.next;
        }

        this.size++;
        return true;

    }

    @Override
    public boolean addLast(E element) {
        Node<E> newNode = new Node<>(element);

        if (this.size == 0) {
            this.head = newNode;
            this.tail = newNode;
        } else if (this.size == 1) {
            newNode.prev = this.head;
            this.head.next = newNode;
            this.tail = newNode;
        } else {
            this.tail.next = newNode;
            newNode.prev = this.tail;
            this.tail = newNode;
        }
        this.size++;
        return true;
    }

    //RETRIEVE

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public E get(int index) {
        checkIndex(index);
        Node<E> node;

        if (index <= this.size / 2) {
            node = this.head;
            for (int j = 0; j < index; j++) {
                node = node.next;
            }
        } else {
            node = this.tail;
            for (int j = 0; j < this.size - 1 - index; j++) {
                node = node.prev;
            }
        }

        return node.element;

    }

    @Override
    public boolean contains(E element) {
        Node<E> node = this.head;

        while (node != null) {
            if (node.element.equals(element)) {
                return true;
            }
            node = node.next;
        }
        return false;
    }


    @Override
    public int indexOf(E element) {
        Node<E> node = this.head;
        int index = 0;

        while (node != null) {
            if (node.element.equals(element)) {
                return index;
            }
            node = node.next;
            index++;
        }

        return -1;
    }

    @Override
    public int lastIndexOf(E element) {
        int index = this.size - 1;
        Node<E> node = this.tail;
        while (node != null) {
            if (node.element.equals(element)) {
                return index;
            }
            node = node.prev;
            index--;
        }

        return -1;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node<E> node = head;

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

    @Override
    public Object[] toArray() {
        Object[] arr = new Object[this.size];
        Node<E> node = this.head;
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (E) node.element;
            node = node.next;
        }

        return arr;
    }

    //UPDATE

    @Override
    public E set(int index, E element) {
        checkIndex(index);
        Node<E> node;
        if (index < this.size / 2) {
            node = this.head;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
        } else {
            node = this.tail;
            for (int i = 0; i < this.size - 1 - index; i++) {
                node = node.prev;
            }
        }

        E e = node.element;
        node.element = element;

        return e;

    }

    @Override
    public boolean replace(E oldElement, E newElement) {
        ensureNonEmpty();
        Node<E> node = this.head;
        while (node != null) {
            if (node.element.equals(oldElement)) {
                node.element = newElement;
                return true;
            }
            node = node.next;
        }
        return false;
    }


    @Override
    public boolean replaceAll(E oldElement, E newElement) {
        ensureNonEmpty();
        Node<E> node = this.head;
        boolean flag = false;
        while (node != null) {
            if (node.element.equals(oldElement)) {
                node.element = newElement;
                flag = true;
            }
            node = node.next;
        }
        return flag;
    }

    //DELETE

    @Override
    public E remove(int index) {
        checkIndex(index);
        if (index == 0) {
            return removeFirst();
        }
        if (index == this.size - 1) {
            return removeLast();
        }

        Node<E> node = this.head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }

        E element = node.next.element;
        node.next = node.next.next;

        this.size--;
        return element;
    }

    @Override
    public boolean remove(Object element) {
        ensureNonEmpty();
        if (this.head.element.equals(element)) {
            this.head = this.head.next;
            this.size--;
            return true;
        }
        Node<E> node = this.head;


        while (node.next != null) {
            if (node.next.element.equals(element)) {
                node.next = node.next.next;
                this.size--;
                return true;
            }
            node = node.next;
        }
        return false;

    }

    @Override
    public E removeFirst() {
        ensureNonEmpty();
        Node<E> tmp = this.head;
        this.head = head.next;
        this.size--;
        return tmp.element;

    }

    @Override
    public E removeLast() {
        ensureNonEmpty();

        if (this.size == 1) {
            E element = this.head.element;
            this.clear();
            return element;
        }

        Node<E> prev = this.head;
        Node<E> node = this.head.next;
        while (node.next != null) {
            node = node.next;
            prev = prev.next;
        }

        E element = node.element;
        prev.next = null;
        this.size--;
        return element;
    }

    @Override
    public void clear() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    @Override
    public boolean removeAll(E element) {
        ensureNonEmpty();
        if (this.size == 1) {
            if (this.head.element.equals(element)) {
                this.clear();
                return true;
            }
            return false;
        }
        boolean flag = false;

        while (this.head.element.equals(element)) {
            this.head = this.head.next;
            this.size--;
            flag = true;
        }

        Node<E> prev = this.head;
        Node<E> node = this.head.next;

        while (node != null) {
            if (node.element.equals(element)) {
                prev.next = node.next;
                node = prev.next;
                flag = true;
                this.size--;
            } else {
                prev = prev.next;
                node = node.next;
            }
        }


        return flag;
    }

    //HELPERS

    public void ensureNonEmpty() {
        if (this.size == 0) {
            throw new IllegalStateException();
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException();
        }
    }
}
