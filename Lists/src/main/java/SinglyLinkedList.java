import java.util.Iterator;

public class SinglyLinkedList<E> implements List<E> {
    private Node<E> head;
    private int size;

    private static class Node<E> {
        private E element;
        private Node<E> next;

        public Node(E element) {
            this.element = element;
            this.next = null;
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
        Node<E> node = this.head;
        int i = 0;
        while (i++ < index) {
            node = node.next;
        }
        Node<E> newNode = new Node<>(element);
        Node<E> tmp = node.next;
        node.next = newNode;
        newNode.next = tmp;

        this.size++;
        return true;
    }

    @Override
    public boolean addFirst(E element) {
        Node<E> newNode = new Node<>(element);
        if (this.size == 0) {
            this.head = newNode;
        } else {
            newNode.next = this.head;
            this.head = newNode;
        }
        this.size++;
        return true;

    }

    @Override
    public boolean addLast(E element) {
        Node<E> newNode = new Node<>(element);

        if (this.size == 0) {
            this.head = newNode;
        } else {
            Node<E> node = this.head;
            while (node.next != null) {
                node = node.next;
            }
            node.next = newNode;
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
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException();
        }
        Node<E> node = this.head;
        int i = 0;
        while (i != index) {
            i++;
            node = node.next;
        }

        return node.element;

    }

    @Override
    public boolean contains(Object o) {
        Node<E> node = this.head;

        while (node != null) {
            if (node.element.equals(o)) {
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
        int lastIndex = -1;

        int index = 0;
        Node<E> node = this.head;
        while (node != null) {
            if (node.element.equals(element)) {
                lastIndex = index;
            }
            node = node.next;
            index++;
        }

        return lastIndex;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node<E> node = head;

            @Override
            public boolean hasNext() {
                return this.node.next != null;
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
        for (int i = 0; i < this.size; i++) {
            arr[i] = node.element;
            node = node.next;
        }

        return arr;
    }

    //UPDATE

    @Override
    public E set(int index, E element) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException();
        }
        Node<E> newNode = new Node<>(element);
        if (index == 0) {
            newNode.next = this.head;
            this.head = newNode;
        } else {
            Node<E> node = this.head;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
            newNode.next = node.next;
            node.next = newNode;
        }

        return element;

    }

    @Override
    public boolean replace(E oldElement, E newElement) {
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
        Node<E> node = this.head;
        boolean success = false;
        while (node != null) {
            if (node.element.equals(oldElement)) {
                node.element = newElement;
                success = true;
            }
            node = node.next;
        }
        return success;
    }

    //DELETE

    @Override
    public E remove(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            E element = this.head.element;
            this.head = this.head.next;
            this.size--;
            return element;
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
        return false;
    }

    @Override
    public void clear() {
        this.head = null;
        this.size = 0;
    }

    @Override
    public boolean removeAll(E element) {
        return false;
    }
}
