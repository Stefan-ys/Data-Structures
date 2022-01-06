import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;


public class SinglyLinkedListTest extends TestCase {
    private static final int EVEN = 100;
    private static final int UNEVEN = 101;

    private List<Integer> createList(int n) {
        List<Integer> list = new SinglyLinkedList<>();
        for (int i = 0; i < n; i++) {
            list.add(i);
        }
        return list;
    }

    @Test
    public void testAdd() {
        List<Integer> list = createList(EVEN);

        for (int i = 0; i < EVEN; i++) {
            Assert.assertEquals(i, (int) list.get(i));
        }

        list.clear();

        for (int i = 0; i < 9; i++) {
            if (i < 3) {
                list.add(0, 13);
            } else if (i < 6) {
                list.add(list.size(), 42);
            } else {
                list.add(list.size() / 2 - 1, 69);
            }
        }
        for (int i = 0; i < 9; i++) {
            if (i < 3) {
                Assert.assertEquals(13, (int) list.get(i));
            } else if (i < 6) {
                Assert.assertEquals(69, (int) list.get(i));
            } else {
                Assert.assertEquals(42, (int) list.get(i));
            }
        }

    }

    @Test
    public void testAddFirst() {
        List<Integer> list = createList(EVEN);
        Assert.assertEquals(0, (int) list.get(0));
    }

    @Test
    public void testAddLast() {
        List<Integer> list = createList(EVEN);
        Assert.assertEquals(EVEN - 1, (int) list.get(EVEN - 1));
    }

    @Test
    public void testSize() {
        List<Integer> list = new SinglyLinkedList<>();
        Assert.assertEquals(0, list.size());
        list = createList(EVEN);
        Assert.assertEquals(EVEN, list.size());

    }

    @Test
    public void testIsEmpty() {
        List<Integer> list = new SinglyLinkedList<>();
        Assert.assertTrue(list.isEmpty());
        list.add(1);
        Assert.assertFalse(list.isEmpty());
    }

    @Test
    public void testContains() {
        List<Integer> list = createList(EVEN);
        Assert.assertFalse(list.contains(EVEN));
        Assert.assertTrue(list.contains(EVEN - 1));
    }

    @Test
    public void testIndexOf() {
        List<Integer> list = createList(EVEN);
        for (int i = 0; i < EVEN; i++) {
            Assert.assertEquals(i, list.indexOf(i));
        }
    }

    @Test
    public void testLastIndexOf() {
        List<Integer> list = new SinglyLinkedList<>();
        for (int i = 0; i < 5; i++) {
            list.add(42);
        }
        list.add(13);
        Assert.assertEquals(-1, list.lastIndexOf(69));
        Assert.assertEquals(4, list.lastIndexOf(42));
    }


    @Test
    public void testToArray() {
        List<Integer> list = createList(EVEN);
        Integer[] arr = (Integer[]) list.toArray();
        for (int i = 0; i < EVEN; i++) {
            Assert.assertEquals(arr[i], list.get(i));
        }
    }

    @Test
    public void testSet() {
        List<Integer> list = createList(EVEN);
        for (int i = 0; i < list.size(); i++) {
            list.set(i, 42);
        }
        for (int i = 0; i < list.size(); i++) {
            Assert.assertEquals(41, (int) list.get(i));
        }
    }

    @Test
    public void testReplace() {

    }

    @Test
    public void testReplaceAll() {

    }

    @Test
    public void testRemove() {
    }

    @Test
    public void testTestRemove() {
    }

    @Test
    public void testClear() {
    }

    @Test
    public void testRemoveAll() {
    }
}