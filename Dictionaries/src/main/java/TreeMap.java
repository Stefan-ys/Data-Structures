import interfaces.Map;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TreeMap<K extends Comparable<K>, V> implements Map<K, V> {

    /*              RULES
        - EVERY NODE IS RED OR BLACK
        - ROOT IS ALWAYS BLACK
        - NEW INSERTIONS ARE ALWAYS RED
        - EVERY PATH FROM ROOT - LEAF HAS THE SAME NUMBER OF BLACK NODES
        - NO PATH CAN HAVE TWO CONSECUTIVE RED NODES
        - NULLS ARE BLACK

        REBALANCE:
            BLACK AUNT: ROTATE
                -AFTER ROTATION: PARENT IS BLACK CHILDREN ARE RED

            RED AUNT: COLOR FLIP
                -AFTER COLOR FLIP: PARENT IS RED CHILDREN ARE BLACK
    */

    private static final boolean RED = true;
    private static final boolean BLACK = false;
    private Node<K, V> root;
    private int size;

    public TreeMap() {
        this.size = 0;
    }

    private static class Node<K extends Comparable<K>, V> {
        private K key;
        private V value;
        private Node<K, V> leftChild;
        private Node<K, V> rightChild;
        private Node<K, V> parent;
        private boolean leftChildB;
        private boolean black;
        private boolean color;
        private int count;


        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.count = 1;
        }

        public boolean isRed() {
            return this.color == RED;
        }
    }

    //CREATE

    @Override
    public void put(K key, V value) {
        this.root = this.insert(this.root, key, value);
        this.root.color = BLACK;
    }
    //RETRIEVE

    @Override
    public V get(K key) {
        Node<K, V> current = this.root;
        while (current != null) {
            if (current.key.equals(key)) {
                break;
            }
            current = key.compareTo(current.key) < 0 ? current.leftChild : current.rightChild;
        }
        if (current == null) {
            throw new IllegalArgumentException();
        }
        return current.value;
    }

    @Override
    public Collection<K> keys() {
        List<K> result = new ArrayList<>();
        inOrderKeys(this.root, result);
        return result;
    }


    @Override
    public Collection<V> values() {
        List<V> result = new ArrayList<>();
        inOrderValues(this.root, result);
        return result;
    }

    @Override
    public boolean containsKey(K key) {
        Node<K, V> current = this.root;
        while (current != null) {
            if (current.key.equals(key)) {
                break;
            }
            current = key.compareTo(current.key) < 0 ? current.leftChild : current.rightChild;
        }
        return current != null;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public int size() {
        return this.size;
    }

    //UPDATE

    //DELETE

    @Override
    public boolean remove(K key) {
        return false;
    }

    @Override
    public void clear() {
        this.root = null;
        this.size = 0;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    //HELPERS

    private Node<K, V> insert(Node<K, V> node, K key, V value) {
        if (node == null) {
            return new Node<>(key, value);
        }

        if (node.key.compareTo(key) > 0) {
            node.leftChild = this.insert(node.leftChild, key, value);
        } else if (node.key.compareTo(key) < 0) {
            node.rightChild = this.insert(node.rightChild, key, value);
        }

        if (!isRed(node.leftChild) && isRed(node.rightChild)) {
            node = rotateLeft(node);
        }
        if (isRed(node.leftChild) && isRed(node.leftChild.leftChild)) {
            node = rotateRight(node);
        }
        if (isRed(node.leftChild) && isRed(node.rightChild)) {
            node = flipColors(node);
        }
        node.count = getNodesCount(node.leftChild) + getNodesCount(node.rightChild) + 1;

        return node;
    }

    private Node<K, V> rotateLeft(Node<K, V> node) {
        Node<K, V> result = node.rightChild;
        node.rightChild = result.leftChild;
        result.leftChild = node;

        result.color = BLACK;
        node.color = RED;

        node.count = getNodesCount(node.leftChild) + getNodesCount(node.rightChild) + 1;
        result.count = getNodesCount(result.leftChild) + getNodesCount(result.rightChild) + 1;

        return result;
    }

    private Node<K, V> rotateRight(Node<K, V> node) {
        Node<K, V> result = node.leftChild;
        node.leftChild = result.rightChild;
        result.rightChild = node;

        result.color = BLACK;
        node.color = RED;

        node.count = getNodesCount(node.leftChild) + getNodesCount(node.rightChild) + 1;
        result.count = getNodesCount(result.leftChild) + getNodesCount(result.rightChild) + 1;

        return result;

    }

    private Node<K, V> flipColors(Node<K, V> node) {
        node.color = RED;
        node.leftChild.color = BLACK;
        node.rightChild.color = BLACK;

        return node;
    }

    private int getNodesCount(Node<K, V> node) {
        if (node == null) {
            return 0;
        }
        return node.count;
    }

    private boolean isRed(Node<K, V> node) {
        return node != null && node.isRed();
    }

    private void inOrderKeys(Node<K, V> node, List<K> list) {
        if (node == null) {
            return;
        }
        this.inOrderKeys(node.leftChild, list);
        list.add(node.key);
        this.inOrderKeys(node.rightChild, list);
    }

    private void inOrderValues(Node<K, V> node, List<V> list) {
        if (node == null) {
            return;
        }
        this.inOrderValues(node.leftChild, list);
        list.add(node.value);
        this.inOrderValues(node.rightChild, list);
    }
}
