
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

public class AVLTreeTest {
    private static final Integer[] INSERTORDER = new Integer[]{60, 16, 70, 90, 77, 38, 96, 84, 50, 15, 30, 82, 91, 10, 54, 20};
    private static final Integer[] PREORDER = new Integer[]{60, 16, 15, 10, 38, 30, 20, 50, 54, 84, 77, 70, 82, 91, 90, 96};
    private static final Integer[] INORDER = new Integer[]{10, 15, 16, 20, 30, 38, 50, 54, 60, 70, 77, 82, 84, 90, 91, 96};
    private static final Integer[] POSTORDER = new Integer[]{10, 15, 20, 30, 54, 50, 38, 16, 70, 82, 77, 90, 96, 91, 84, 60};
    private static final Integer[] LEVELORDER = new Integer[]{60, 16, 84, 15, 38, 77, 91, 10, 30, 50, 70, 82, 90, 96, 20, 54};

    private AVLTree<Integer> createAVLTree() {
        AVLTree<Integer> avlTree = new AVLTree<>();
        for (Integer integer : INSERTORDER) {
            avlTree.insert(integer);
        }
        return avlTree;

    }


    @Test
    public void testContains() {
        AVLTree<Integer> avlTree = createAVLTree();
        for (Integer integer : PREORDER) {
            Assert.assertTrue(avlTree.contains(integer));
        }
        Assert.assertFalse(avlTree.contains(-1));
    }

    @Test
    public void testPreOrder() {
        AVLTree<Integer> avlTree = createAVLTree();
        List<Integer> list = (List<Integer>) avlTree.preOrder();

        Assert.assertEquals(PREORDER.length, list.size());

        for (int i = 0; i < list.size(); i++) {
            Assert.assertEquals(PREORDER[i], list.get(i));
        }

    }

    @Test
    public void testInOrder() {
        AVLTree<Integer> avlTree = createAVLTree();
        List<Integer> list = (List<Integer>) avlTree.inOrder();

        Assert.assertEquals(INORDER.length, list.size());

        for (int i = 0; i < list.size(); i++) {
            Assert.assertEquals(INORDER[i], list.get(i));
        }
    }

    @Test
    public void testPostOrder() {
        AVLTree<Integer> avlTree = createAVLTree();
        List<Integer> list = (List<Integer>) avlTree.postOrder();

        Assert.assertEquals(POSTORDER.length, list.size());
        for (int i = 0; i < list.size(); i++) {
            Assert.assertEquals(POSTORDER[i], list.get(i));
        }
    }

    @Test
    public void testLevelOrder() {
        AVLTree<Integer> avlTree = createAVLTree();
        List<Integer> list = (List<Integer>) avlTree.levelOrder();

        Assert.assertEquals(LEVELORDER.length, list.size());
        for (int i = 0; i < list.size(); i++) {
            Assert.assertEquals(LEVELORDER[i], list.get(i));
        }
    }

    @Test
    public void testGetMin() {
        AVLTree<Integer> avlTree = createAVLTree();
        int min = avlTree.minValue();
        Assert.assertEquals(10, min);
    }

    @Test
    public void testGetMax() {
        AVLTree<Integer> avlTree = createAVLTree();
        int max = avlTree.maxValue();
        Assert.assertEquals(96, max);
    }


    @Test
    public void testIsEmpty() {

    }

    @Test
    public void testRemove() {
    }

}
