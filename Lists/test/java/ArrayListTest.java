import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

public class ArrayListTest extends TestCase {
    private static final int SIZE = 10;


    private List<Integer> createList(int n) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(i);
        }
        return list;
    }

    @Test
    public void testAdd() {
        List<Integer> list = createList(SIZE);

        for (int i = 0; i < SIZE; i++) {
            Assert.assertEquals(i, (int) list.get(i));
        }

    }

    @Test
    public void testTestAdd() {
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            if (i < 3) {
                list.add(0, 13);
            } else if (i < 6) {
                list.add(list.size(), 42);
            } else {
                list.add(list.size() / 2 , 69);
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
        List<Integer> list = createList(SIZE);
        Assert.assertEquals(0, (int) list.get(0));
    }

    @Test
    public void testAddLast() {
        List<Integer> list = createList(SIZE);
        Assert.assertEquals(SIZE - 1, (int) list.get(SIZE - 1));
    }


    @Test
    public void testContains() {
        List<Integer> list = createList(SIZE);
        Assert.assertFalse(list.contains(SIZE));
        Assert.assertTrue(list.contains(SIZE - 1));
    }

    @Test
    public void testIndexOf() {
        List<Integer> list = createList(SIZE);
        for (int i = 0; i < SIZE; i++) {
            Assert.assertEquals(i, list.indexOf(i));
        }
    }

    @Test
    public void testLastIndexOf() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(42);
        }
        list.add(13);
        Assert.assertEquals(-1, list.lastIndexOf(69));
        Assert.assertEquals(4, list.lastIndexOf(42));
    }


    @Test
    public void testToArray() {
        List<Integer> list = createList(SIZE);

        Object[] arr = list.toArray();

        for (int i = 0; i < SIZE; i++) {
            Assert.assertEquals(arr[i], list.get(i));
        }
    }

    @Test
    public void testSet() {
        List<Integer> list = createList(SIZE);
        for (int i = 0; i < list.size(); i++) {
            Assert.assertEquals(i, (int) list.set(i, 42));
        }
        for (int i = 0; i < list.size(); i++) {
            Assert.assertEquals(42, (int) list.get(i));
        }
    }

    @Test
    public void testReplace() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                list.add(1);
            } else {
                list.add(2);
            }
        }
        list.replace(1, 3);


        Assert.assertEquals(3, (int) list.get(0));
        for (int i = 1; i < list.size(); i++) {
            if (i % 2 == 0) {
                Assert.assertEquals(1, (int) list.get(i));
            } else {
                Assert.assertEquals(2, (int) list.get(i));
            }
        }
    }

    @Test
    public void testReplaceAll() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                list.add(1);
            } else {
                list.add(2);
            }
        }
        list.replaceAll(1, 3);

        for (int i = 0; i < list.size(); i++) {
            if (i % 2 == 0) {
                Assert.assertEquals(3, (int) list.get(i));
            } else {
                Assert.assertEquals(2, (int) list.get(i));
            }
        }
    }

    @Test
    public void testRemove() {
        List<Integer> list = createList(SIZE);

        while (!list.isEmpty()) {
            Integer e = list.remove(0);
            Assert.assertFalse(list.contains(e));
        }

        list = createList(SIZE);
        while ((!list.isEmpty())) {
            Integer e = list.remove(Math.max(0, list.size() / 2 - 1));
            Assert.assertFalse(list.contains(e));
        }

        list = createList(SIZE);
        while ((!list.isEmpty())) {
            Integer e = list.remove(list.size() - 1);
            Assert.assertFalse(list.contains(e));
        }

        Assert.assertTrue(list.isEmpty());
    }

    @Test
    public void testTestRemove() {
        List<Integer> list = createList(SIZE);
        for (int i = 0; i < SIZE; i++) {
            Assert.assertTrue(list.remove((Object) i));
        }
        Assert.assertTrue(list.isEmpty());
    }

    @Test
    public void testClear() {
        List<Integer> list = createList(SIZE);
        Assert.assertEquals(SIZE, list.size());
        Assert.assertFalse(list.isEmpty());
        list.clear();
        Assert.assertEquals(0, list.size());
        Assert.assertTrue(list.isEmpty());
    }

    @Test
    public void testRemoveAll() {
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            if (i % 2 == 0) {
                list.add(42);
            } else {
                list.add(i);
            }
        }
        list.removeAll(42);
        Assert.assertFalse(list.contains(42));
        Assert.assertEquals(10, list.size());
    }
}
