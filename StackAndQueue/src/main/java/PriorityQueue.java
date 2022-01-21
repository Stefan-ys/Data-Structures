import interfaces.Queue;

import java.util.*;

public class PriorityQueue<E extends Comparable<E>> implements Queue<E> {
    private List<E> elements;

    public PriorityQueue() {
        this.elements = new ArrayList<>();
    }

    //CREATE
    @Override
    public boolean offer(E element) {
        this.elements.add(element);
        this.heapifyUp(this.size() - 1);
        return true;
    }


    //RETRIEVE
    @Override
    public E peek() {
        if (this.isEmpty()) {
            throw new IllegalStateException();
        }
        return this.elements.get(0);
    }

    @Override
    public int size() {
        return this.elements.size();
    }

    @Override
    public boolean isEmpty() {
        return this.elements.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return this.elements.contains(o);
    }

    @Override
    public Object[] toArray() {
        return this.elements.toArray();
    }

    @Override
    public Iterator iterator() {
        return this.elements.iterator();
    }

    // UPDATE

    //DELETE

    @Override
    public void clear() {
        this.elements.clear();
    }

    @Override
    public E poll() {
        if (this.isEmpty()) {
            throw new IllegalStateException();
        }
        E element = this.elements.get(0);

        Collections.swap(this.elements, 0, this.elements.size() - 1);
        this.elements.remove(this.elements.size() - 1);
        this.heapifyDown(0);
        return element;
    }

    //HELPERS

    private int getParentIndex(int index) {
        return (index - 1) / 2;
    }

    private int getLeftChild(int index) {
        return index * 2 + 1;
    }

    private int getRightChild(int index) {
        return index * 2 + 2;
    }

    private boolean isLess(int index1, int index2) {
        return this.elements.get(index1).compareTo(this.elements.get(index2)) > 0;
    }


    private void heapifyUp(int index) {
        while (index > 0 && isLess(getParentIndex(index), index)) {
            int parent = getParentIndex(index);
            Collections.swap(this.elements, parent, index);
            index = parent;
        }
    }


    private void heapifyDown(int index) {
        while (getLeftChild(index) < this.size()) {
            int leftChildIndex = getLeftChild(index);
            int child = isLess(leftChildIndex, index) ? index : leftChildIndex;

            int rightChildIndex = getRightChild(index);
            if (rightChildIndex < this.size() && isLess(child, rightChildIndex)) {
                child = rightChildIndex;
            }
            if (index == child) {
                break;
            }
            Collections.swap(this.elements, index, child);
            index = child;
        }
    }
}
