import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;

public class QueueTest extends TestCase {
    int size = 10;

    private Queue<Integer> createQueue(int size) {
        Queue<Integer> queue = new Queue<>();
        for (int i = 0; i < size; i++) {
            queue.offer(i);
        }
        return queue;
    }

    @Test
    public void testOffer() {
        Queue<Integer> queue = createQueue(size);
        Assert.assertEquals(0, (int) queue.peek());

    }

    @Test
    public void testSize() {
        Queue<Integer> queue = new Queue<>();
        Assert.assertEquals(0, queue.size());
        queue = createQueue(size);
        Assert.assertEquals(size, queue.size());
    }

    @Test
    public void testIsEmpty() {
        Queue<Integer> queue = createQueue(size);
        Assert.assertFalse(queue.isEmpty());
        queue.clear();
        Assert.assertTrue(queue.isEmpty());
    }

    @Test
    public void testContains() {
        Queue<Integer> queue = createQueue(size);
        Assert.assertFalse(queue.contains(size));
        Iterator<Integer> iterator = queue.iterator();
        for (int i = 0; i < size; i++) {
            Assert.assertEquals(i, (int) iterator.next());
        }
    }

    @Test
    public void testToArray() {
        Queue<Integer> stack = createQueue(size);
        Object[] arr = stack.toArray();
        for (int i = 0; i < arr.length; i++) {
            Assert.assertEquals(arr[i], i);
        }
    }


    @Test
    public void testPoll() {
        Queue<Integer> queue = createQueue(size);
        int i = size - 1;
        while (!queue.isEmpty()) {
            Assert.assertEquals(size - 1 - i--, (int) queue.poll());
        }
    }
}