import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class BinaryTreeTest {
    private static final Integer[] PREORDER = new Integer[]{25, 15, 10, 4, 12, 22, 18, 24, 50, 35, 31, 44, 70, 66, 90};
    private static final Integer[] INORDER = new Integer[]{4, 10, 12, 15, 18, 22, 24, 25, 31, 35, 44, 50, 66, 70, 90};
    private static final Integer[] POSTORDER = new Integer[]{4, 12, 10, 18, 24, 22, 15, 31, 44, 35, 66, 90, 70, 50, 25};
    private static final Integer[] LEVELORDER = new Integer[]{25, 15, 50, 10, 22, 35, 70, 4, 12, 18, 24, 31, 44, 66, 90};

    private BinaryTree<Integer> createBinaryTree() {
        BinaryTree<Integer> binaryTree = new BinaryTree<>();
        for (Integer integer : PREORDER) {
            binaryTree.insert(integer);
        }
        return binaryTree;

    }


    @Test
    public void testContains() {
        BinaryTree<Integer> binaryTree = createBinaryTree();
        for (Integer integer : PREORDER) {
            Assert.assertTrue(binaryTree.contains(integer));
        }
        Assert.assertFalse(binaryTree.contains(-1));
    }

    @Test
    public void testPreOrder() {
        BinaryTree<Integer> binaryTree = createBinaryTree();
        List<Integer> list = (List<Integer>) binaryTree.preOrder();
        Assert.assertEquals(PREORDER.length, list.size());

        for (int i = 0; i < list.size(); i++) {
            Assert.assertEquals(PREORDER[i], list.get(i));
        }

    }

    @Test
    public void testInOrder() {
        BinaryTree<Integer> binaryTree = createBinaryTree();
        List<Integer> list = (List<Integer>) binaryTree.inOrder();
        Assert.assertEquals(INORDER.length, list.size());

        for (int i = 0; i < list.size(); i++) {
            Assert.assertEquals(INORDER[i], list.get(i));
        }
    }

    @Test
    public void testPostOrder() {
        BinaryTree<Integer> binaryTree = createBinaryTree();
        List<Integer> list = (List<Integer>) binaryTree.postOrder();
        Assert.assertEquals(POSTORDER.length, list.size());

        for (int i = 0; i < list.size(); i++) {
            Assert.assertEquals(POSTORDER[i], list.get(i));
        }
    }

    @Test
    public void testLevelOrder() {
        BinaryTree<Integer> binaryTree = createBinaryTree();
        List<Integer> list = (List<Integer>) binaryTree.levelOrder();
        Assert.assertEquals(LEVELORDER.length, list.size());
        for (int i = 0; i < list.size(); i++) {
            Assert.assertEquals(LEVELORDER[i], list.get(i));
        }
    }


    @Test
    public void testIsEmpty() {

    }

    @Test
    public void testRemove() {
    }
}