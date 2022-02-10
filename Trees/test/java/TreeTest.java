import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class TreeTest {
    private Tree<Integer> createTree() {
        Tree<Integer> tree = new Tree<>(1);
        tree.insert(1, 2);
        tree.insert(1, 3);
        tree.insert(1, 4);
        tree.insert(2, 5);
        tree.insert(5, 6);
        tree.insert(5, 7);
        tree.insert(5, 8);
        tree.insert(4, 9);
        tree.insert(4, 10);
        tree.insert(10, 11);
        tree.insert(10, 12);
        tree.insert(12, 13);
        tree.insert(12, 14);
        tree.insert(12, 15);
        return tree;
    }

    @Test
    public void testContains() {
        Tree<Integer> tree = createTree();
        for (int i = 1; i <= 15; i++) {
            Assert.assertTrue(tree.contains(i));
        }
        Assert.assertFalse(tree.contains(-1));
        Assert.assertFalse(tree.contains(0));
        Assert.assertFalse(tree.contains(16));

    }

    @Test
    public void testBreathFirstSearch() {
        Tree<Integer> tree = createTree();
        List<Integer> list = (List<Integer>) tree.breathFirstSearch();
        Integer[] arr = new Integer[]{1, 2, 3, 4, 5, 9, 10, 6, 7, 8, 11, 12, 13, 14, 15};

        for (int i = 0; i < arr.length; i++) {
            Assert.assertEquals(arr[i], list.get(i));
        }
    }


    @Test
    public void testDepthFirstSearch() {
        Tree<Integer> tree = createTree();
        List<Integer> list = (List<Integer>) tree.depthFirstSearch();
        Integer[] arr = new Integer[]{1, 2, 5, 6, 7, 8, 3, 4, 9, 10, 11, 12, 13, 14, 15};

        for (int i = 0; i < arr.length; i++) {
            Assert.assertEquals(arr[i], list.get(i));
        }
    }

    @Test
    public void testSize() {
        Tree<Integer> tree = createTree();
        Assert.assertEquals(15, tree.size());
    }


    @Test
    public void testRemove() {
        Tree<Integer> tree = createTree();
        tree.remove(12);
        Assert.assertEquals(11, tree.size());

        tree.remove(10);
        Assert.assertEquals(9, tree.size());
        tree.remove(2);
        List<Integer> list = (List<Integer>) tree.breathFirstSearch();
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + "  ");
        }
        Assert.assertEquals(4, tree.size());
    }
}