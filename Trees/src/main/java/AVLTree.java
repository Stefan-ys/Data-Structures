import interfaces.Tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AVLTree<E extends Comparable<E>> implements Tree<E> {
    private Node<E> root;
    private int size;

    private static class Node<E> {
        private E element;
        private Node<E> parent;
        private Node<E> leftChild;
        private Node<E> rightChild;
        private int height;

        public Node(E element) {
            this.element = element;
            this.leftChild = null;
            this.rightChild = null;
            this.height = 1;
        }
    }

    //CREATE

    @Override
    public void insert(E element) {
        insert(this.root, element);
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

    private Node<E> insert(Node<E> node, E element) {
        if (node == null) {
            return new Node<>(element);
        }
        if (element.compareTo(node.element) < 0) {
            node.leftChild = this.insert(node.leftChild, element);
        } else if (element.compareTo(node.element) > 0) {
            node.rightChild = this.insert(node.rightChild, element);
        }
        updateHeight(node);
        node = balance(node);
        return node;

    }

    private Node<E> balance(Node<E> node) {
        int balance = height(node.leftChild) - height(node.rightChild);

        if (balance > 1) {
            int childBalance = height(node.leftChild.leftChild) - height(node.leftChild.rightChild);
            if (childBalance < 0) {
                node.leftChild = rightRotate(node.leftChild);
            }
            return rightRotate(node);

        } else if (balance < -1) {
            int childBalance = height(node.rightChild.leftChild) - height(node.rightChild.rightChild);
            if (childBalance > 0) {
                node.rightChild = rightRotate(node.rightChild);
            }
            return leftRotate(node);
        }
        return node;
    }

    private Node<E> leftRotate(Node<E> node) {
        Node<E> tmp = node.leftChild;
        node.leftChild = tmp.rightChild;
        tmp.rightChild = node;

        updateHeight(node);
        updateHeight(tmp);

        return tmp;
    }

    private Node<E> rightRotate(Node<E> node) {
        Node<E> tmp = node.rightChild;
        node.rightChild = tmp.leftChild;
        tmp.leftChild = node;

        updateHeight(node);
        updateHeight(tmp);

        return tmp;
    }

    private void updateHeight(Node<E> node) {
        node.height = Math.max(height(node.leftChild), height(node.rightChild));
    }

    private int height(Node<E> node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

}
