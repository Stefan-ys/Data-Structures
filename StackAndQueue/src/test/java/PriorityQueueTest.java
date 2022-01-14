import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.PriorityQueue;

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
        priorityQueue.offer(-1);
        priorityQueue.offer(55);
        for (Object i : priorityQueue.toArray()) {
            System.out.print(i + " ");
        }
        System.out.println();
        assertEquals(SIZE - 1, (int) priorityQueue.peek());
        priorityQueue.offer(5);
        assertEquals(SIZE - 1, (int) priorityQueue.peek());
        priorityQueue.offer(11);
        assertEquals(11, (int) priorityQueue.peek());
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