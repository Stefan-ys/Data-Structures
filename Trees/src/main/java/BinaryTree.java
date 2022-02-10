import interfaces.Tree;
import org.w3c.dom.Node;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BinaryTree<E extends Comparable<E>> implements Tree<E> {
    private Node<E> root;
    private int size;

    public BinaryTree() {
        this.size = 0;
    }

    private static class Node<E> {
        private E element;
        private Node<E> parent;
        private Node<E> leftChild;
        private Node<E> rightChild;

        public Node(E element) {
            this.element = element;
            this.parent = null;
            this.leftChild = null;
            this.rightChild = null;
        }
    }

    //CREATE

    @Override
    public void insert(E element) {
        if (this.root == null) {
            this.root = new Node<>(element);
            return;
        }
        Node<E> node = this.root;

        while (true) {
            if (element.compareTo(node.element) < 0) {
                if (node.leftChild == null) {
                    node.leftChild = new Node<>(element);
                    node.leftChild.parent = node;
                    this.size++;
                    return;
                }
                node = node.leftChild;
            } else if (element.compareTo(node.element) > 0) {
                if (node.rightChild == null) {
                    node.rightChild = new Node<>(element);
                    node.leftChild.parent = node;
                    this.size++;
                    return;
                }
                node = node.rightChild;
            } else {
                return;
            }
        }
    }

    //RETRIEVE

    @Override
    public boolean contains(E element) {
        return getNode(element) != null;
    }

    @Override
    public Collection<E> preOrder() {
        Node<E> node = this.root;
        List<E> list = new ArrayList<>();
        preOrder(node, list);
        return list;
    }

    @Override
    public Collection<E> inOrder() {
        Node<E> node = this.root;
        List<E> list = new ArrayList<>();
        inOrder(node, list);
        return list;
    }

    @Override
    public Collection<E> postOrder() {
        Node<E> node = this.root;
        List<E> list = new ArrayList<>();
        postOrder(node, list);
        return list;
    }

    @Override
    public Collection<E> levelOrder() {
        List<E> list = new ArrayList<>();
        ArrayDeque<Node<E>> deque = new ArrayDeque<>();
        deque.offer(this.root);
        while (!deque.isEmpty()) {
            Node<E> node = deque.poll();
            list.add(node.element);
            if (node.leftChild != null) {
                deque.offer(node.leftChild);
            }
            if (node.rightChild != null) {
                deque.offer(node.rightChild);
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

    //DELETE

    @Override
    public boolean remove(E element) {
        Node<E> node = getNode(element);
        if (node == null) {
            return false;
        }

        node.parent.rightChild = node.rightChild;
        Node<E> leftBranch = node.rightChild.leftChild;
        while (leftBranch != null) {
            leftBranch = leftBranch.leftChild;
        }
        leftBranch.parent.leftChild = node.leftChild;

        return true;
    }

    //HELPERS
    private Node<E> getNode(E element) {
        Node<E> node = this.root;

        while (node != null) {
            if (element.compareTo(node.element) < 0) {
                node = node.leftChild;
            } else if (element.compareTo(node.element) > 0) {
                node = node.rightChild;
            } else {
                return node;
            }
        }
        return null;
    }

    private void preOrder(Node<E> node, List<E> list) {
        if (node == null) {
            return;
        }
        list.add(node.element);
        preOrder(node.leftChild, list);
        preOrder(node.rightChild, list);
    }

    private void inOrder(Node<E> node, List<E> list) {
        if (node == null) {
            return;
        }
        inOrder(node.leftChild, list);
        list.add(node.element);
        inOrder(node.rightChild, list);
    }


    private void postOrder(Node<E> node, List<E> list) {
        if (node == null) {
            return;
        }
        postOrder(node.leftChild, list);
        postOrder(node.rightChild, list);
        list.add(node.element);
    }

}
