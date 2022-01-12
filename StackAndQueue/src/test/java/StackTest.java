import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.util.Stack;

public class StackTest extends TestCase {
    int size = 10;

    private Stack<Integer> createStack(int size) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < size; i++) {
            stack.push(i);
        }
        return stack;
    }


    @Test
    public void testPush() {
        Stack<Integer> stack = createStack(size);
        Assert.assertEquals(9, (int) stack.peek());
    }

    @Test
    public void testSize() {
        Stack<Integer> stack = new Stack<>();
        Assert.assertEquals(0, stack.size());
        stack = createStack(size);
        Assert.assertEquals(size, stack.size());
    }

    @Test
    public void testPeek() {
        Stack<Integer> stack = createStack(size);
        Assert.assertEquals(size - 1, (int) stack.peek());
    }

    @Test
    public void testIsEmpty() {
        Stack<Integer> stack = new Stack<>();
        Assert.assertTrue(stack.isEmpty());
        stack = createStack(size);
        Assert.assertFalse(stack.isEmpty());
    }

    @Test
    public void testContains() {
        Stack<Integer> stack = createStack(size);
        Assert.assertFalse(stack.contains(size + 1));
        Assert.assertTrue(stack.contains(0));
    }

    @Test
    public void testToArray() {
        Stack<Integer> stack = createStack(size);
        Object[] arr = stack.toArray();
        for (int i = 0; i < arr.length; i++) {
            Assert.assertEquals(arr[i], i);
        }
    }

    @Test
    public void testPop() {
        Stack<Integer> stack = createStack(size);
        int n = size - 1;
        while (!stack.isEmpty()) {
            Assert.assertEquals(n--, (int) stack.pop());
        }

    }

}