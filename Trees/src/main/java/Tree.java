import interfaces.SimpleTree;

import java.util.*;

public class Tree<E> implements SimpleTree<E> {
    private Node<E> root;
    private int size;

    public Tree(E element) {
        this.root = new Node<>(element);
        this.size = 1;
    }

    private static class Node<E> {
        private E element;
        private Node<E> parent;
        private List<Node<E>> children;

        public Node(E element) {
            this.element = element;
            children = new LinkedList<>();
        }
    }


    //CREATE
    @Override
    public boolean insert(E parent, E element) {
        if (this.root == null) {
            throw new IllegalArgumentException();
        }
        if (parent == null) {
            this.root = new Node<>(element);
        }
        Node<E> node = BFS(parent);
        if (node == null) {
            return false;
        }
        Node<E> newNode = new Node<>(element);
        newNode.parent = node;
        node.children.add(newNode);
        this.size++;
        return true;
    }

    //RETRIEVE
    @Override
    public boolean contains(E element) {
        return BFS(element) != null;
    }

    @Override
    public Collection<E> BreathFirstSearch() {
        List<E> list = new ArrayList<>();
        ArrayDeque<Node<E>> deque = new ArrayDeque<>();
        deque.offer(this.root);
        while (!deque.isEmpty()) {
            Node<E> node = deque.poll();
            list.add(node.element);
            for (Node<E> child : node.children) {
                deque.offer(child);
            }
        }
        return list;
    }

    @Override
    public Collection<E> DepthFirstSearch() {
        List<E> list = new ArrayList<>();
        ArrayDeque<Node<E>> deque = new ArrayDeque<>();
        deque.push(this.root);
        while (!deque.isEmpty()) {
            Node<E> node = deque.pop();
            list.add(node.element);
            for (Node<E> child : node.children) {
                deque.push(child);
            }
        }
        return list;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    //UPDATE

    //DELETE

    @Override
    public boolean remove(E element) {
        Node<E> node = BFS(element);
        if (node == null) {
            return false;
        }
        if (node == this.root) {
            this.root = null;
            this.size = 0;

        } else {
            node.parent.children.remove(node);
        }
        return true;
    }

    //HELPERS

    private Node<E> BFS(E element) {
        ArrayDeque<Node<E>> deque = new ArrayDeque<>();
        deque.offer(this.root);
        while (!deque.isEmpty()) {
            Node<E> node = deque.poll();
            if (node.element.equals(element)) {
                return node;
            }
            for (Node<E> child : node.children) {
                deque.offer(child);
            }
        }
        return null;
    }
}
