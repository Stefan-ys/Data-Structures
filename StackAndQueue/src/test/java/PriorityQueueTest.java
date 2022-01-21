import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;

public class PriorityQueueTest extends TestCase {
    private final int SIZE = 10;

    private PriorityQueue<Integer> createPriorityQueue(int size) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        for (int i = 0; i < size; i++) {
            priorityQueue.offer(i);
        }
        return priorityQueue;
    }

    @Test
    public void testOffer() {
        PriorityQueue<Integer> priorityQueue = createPriorityQueue(SIZE);
        priorityQueue.offer(SIZE / 2);
        assertEquals(0, (int) priorityQueue.peek());
        priorityQueue.offer(SIZE + 1);
        assertEquals(0, (int) priorityQueue.peek());
        priorityQueue.offer(-1);
        assertEquals(-1, (int) priorityQueue.peek());
    }

    @Test
    public void testClear() {
        PriorityQueue<Integer> priorityQueue = createPriorityQueue(SIZE);
        Assert.assertFalse(priorityQueue.isEmpty());
        Assert.assertEquals(SIZE, priorityQueue.size());
        priorityQueue.clear();
        Assert.assertTrue(priorityQueue.isEmpty());
        Assert.assertEquals(0, priorityQueue.size());
    }

    @Test
    public void testPoll() {
        PriorityQueue<Integer> priorityQueue = createPriorityQueue(SIZE);
        for (int i = 0; i < SIZE; i++) {
            assertEquals(i, (int) priorityQueue.poll());
        }
        Assert.assertTrue(priorityQueue.isEmpty());
    }
}