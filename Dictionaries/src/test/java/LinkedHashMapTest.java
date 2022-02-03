import interfaces.Map;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class LinkedHashMapTest {
    private static final int SIZE = 100;

    private Map<String, Integer> createMap() {
        Map<String, Integer> map = new LinkedHashMap<>();
        int testSize = 100;
        for (int i = 1; i <= testSize; i++) {
            String str = "Test" + i;
            map.put(str, i);
        }
        return map;

    }

    @Test
    public void put() {
        Map<String, Integer> map = createMap();
        for (int i = 1; i <= SIZE; i++) {
            String str = "Test" + i;
            Assert.assertTrue(map.containsKey(str));
            Assert.assertTrue(map.containsValue(i));
        }

    }

    @Test
    public void get() {
        Map<String, Integer> map = createMap();
        for (int i = 1; i <= SIZE; i++) {
            String str = "Test" + i;
            int num = map.get(str);
            Assert.assertEquals(i, num);

        }

    }

    @Test
    public void keys() {
        Map<String, Integer> map = createMap();
        List<String> keys = (List<String>) map.keys();
        for (int i = 1; i <= SIZE; i++) {
            String key = keys.get(i - 1);
            Assert.assertEquals(map.get("Test" + i), map.get(key));
        }

    }


    @Test
    public void remove() {
        Map<String, Integer> map = createMap();
        for (int i = 1; i <= SIZE; i++) {
            String str = "Test" + i;
            Assert.assertTrue(map.containsKey(str));
            map.remove(str);
            Assert.assertFalse(map.containsValue(i));
        }
        Assert.assertTrue(map.isEmpty());

    }
}