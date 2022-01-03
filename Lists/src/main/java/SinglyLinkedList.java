import java.util.Collection;
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
        return this.size();
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public E get(int index) {
        if (index >= this.size || index < 0) {
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
        Node<E> node = new Node<>(element);
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
        return null;
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

        return element;

    }

    @Override
    public boolean replace(E oldElement, E newElement) {
        return false;
    }


    @Override
    public boolean replaceAll(E oldElement, E newElement) {
        return false;
    }

    //DELETE

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
