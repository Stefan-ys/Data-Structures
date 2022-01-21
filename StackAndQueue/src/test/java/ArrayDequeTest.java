import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;

public class ArrayDequeTest extends TestCase {
    private final int SIZE = 10;


    private ArrayDeque<Integer> createDeque(int size) {
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();
        for (int i = 0; i < SIZE; i++) {
            arrayDeque.offer(i);
        }
        return arrayDeque;
    }

    @Test
    public void testOfferTestPush() {
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();
        int size = 150;
        for (int i = 0; i < size; i++) {
            if (i % 2 == 0) {
                arrayDeque.offer(i);
                assertEquals(i, (int) arrayDeque.peekLast());
            } else {
                arrayDeque.push(i);
                assertEquals(i, (int) arrayDeque.peekFirst());
            }

        }
        Assert.assertEquals(size, arrayDeque.size());
    }

    @Test
    public void testSize() {
        ArrayDeque<Integer> arrayDeque = createDeque(SIZE);
        Assert.assertEquals(SIZE, arrayDeque.size());
    }

    @Test
    public void testIsEmpty() {
        ArrayDeque<Integer> arrayDeque = createDeque(SIZE);
        Assert.assertFalse(arrayDeque.isEmpty());
        arrayDeque.clear();
        Assert.assertTrue(arrayDeque.isEmpty());
    }


    @Test
    public void testPoll() {
        ArrayDeque<Integer> arrayDeque = createDeque(SIZE);
        for (int i = SIZE - 1; i >= 0; i--) {
            assertEquals(i, (int) arrayDeque.poll());
        }
        Assert.assertTrue(arrayDeque.isEmpty());
    }

    @Test
    public void testPop() {
        ArrayDeque<Integer> arrayDeque = createDeque(SIZE);
        for (int i = 0; i < SIZE; i++) {
            Iterator iterator = arrayDeque.iterator();
            while (iterator.hasNext()) {
                System.out.print(iterator.next() + " - ");
            }
            System.out.println();
            assertEquals(i, (int) arrayDeque.pop());
        }
        Assert.assertTrue(arrayDeque.isEmpty());
    }

}