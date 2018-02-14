package datastructures.sorting;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.PriorityQueue;

import misc.BaseTest;
import misc.exceptions.EmptyContainerException;
import datastructures.concrete.ArrayHeap;
import datastructures.interfaces.IPriorityQueue;
import org.junit.Test;

/**
 * See spec for details on what kinds of tests this class should include.
 */
public class TestArrayHeapFunctionality extends BaseTest {
    protected <T extends Comparable<T>> IPriorityQueue<T> makeInstance() {
        return new ArrayHeap<>();
    }

    @Test(timeout=SECOND)
    public void testBasicSize() {
        IPriorityQueue<Integer> heap = this.makeInstance();
        heap.insert(3);
        assertEquals(1, heap.size());
        assertTrue(!heap.isEmpty());
    }
    
    @Test(timeout=SECOND)
    public void testRemoveMinBasic() {
        IPriorityQueue<Integer> heap = this.makeInstance();
        heap.insert(3);
        heap.insert(2);
        heap.insert(1);
        assertEquals(3, heap.size());
        assertEquals(1, heap.removeMin());
        assertEquals(2, heap.size());
        assertEquals(2, heap.removeMin());
        assertEquals(1, heap.size());
        assertEquals(3, heap.removeMin());
        assertTrue(heap.isEmpty());
    }
    
    @Test(timeout=SECOND)
    public void testRemoveMinString() {
        IPriorityQueue<String> heap = this.makeInstance();
        heap.insert("ca");
        heap.insert("b");
        heap.insert("a");
        assertEquals(3, heap.size());
        assertEquals("a", heap.removeMin());
        assertEquals(2, heap.size());
        assertEquals("b", heap.removeMin());
        assertEquals(1, heap.size());
        assertEquals("ca", heap.removeMin());
        assertTrue(heap.isEmpty());
    }
    
    @Test(timeout=SECOND)
    public void testRemoveMinOneElement() {
        IPriorityQueue<String> heap = this.makeInstance();
        heap.insert("a");   
        assertEquals(1, heap.size());
        assertEquals("a", heap.removeMin());
        assertTrue(heap.isEmpty());
    }
    
    @Test(timeout=SECOND)
    public void testRemoveMinTwoElements() {
        IPriorityQueue<Double> heap = this.makeInstance();
        heap.insert(1.1);
        heap.insert(2.2);
        assertEquals(2, heap.size());
        assertEquals(1.1, heap.removeMin());
        assertEquals(1, heap.size());
        assertEquals(2.2, heap.removeMin());
        assertTrue(heap.isEmpty());
    }
    
    @Test(timeout=SECOND)
    public void testPeekMinBasic() {
        IPriorityQueue<Integer> heap = this.makeInstance();
        heap.insert(3);
        heap.insert(1);
        heap.insert(2);
        assertEquals(3, heap.size());
        assertEquals(1, heap.peekMin());
        assertEquals(3, heap.size());
    }
    
    @Test(timeout=SECOND)
    public void testInsertSameElementAndRemoveMinRepeated() {
        IPriorityQueue<Character> heap = this.makeInstance();
        int cap = 500;
        for (int i = 1; i <= cap; i++) {
            heap.insert('a');
            assertEquals(i, heap.size());
        }
        assertEquals(cap, heap.size());
        for (int i = 1; i <= cap; i++) {
            assertEquals('a', heap.removeMin());
        }
        assertEquals(0, heap.size());
    }
    
    @Test(timeout=SECOND)
    public void testInsertSameElementsAndRemoveMinRepeatedMany() {
        IPriorityQueue<Character> heap = this.makeInstance();
        int cap = 500;
        for (int i = 1; i <= cap; i++) {
            heap.insert('a');
            assertEquals(3*i-2, heap.size());
            heap.insert('b');
            assertEquals(3*i-1, heap.size());
            heap.insert('c');
            assertEquals(3*i, heap.size());
        }
        assertEquals(3*cap, heap.size());
        for (int i = 1; i <= cap; i++) {
            assertEquals('a', heap.removeMin());
        }
        for (int i = 1; i <= cap; i++) {
            assertEquals('b', heap.removeMin());
        }
        for (int i = 1; i <= cap; i++) {
            assertEquals('c', heap.removeMin());
        }
        assertEquals(0, heap.size());
    }
    
    @Test(timeout=SECOND)
    public void testInsertAndRemoveBasic() {
        IPriorityQueue<Integer> heap = this.makeInstance();
        PriorityQueue<Integer> testqueue = new PriorityQueue<Integer>(5);
        heap.insert(10);
        heap.insert(-1);
        heap.insert(2);
        heap.insert(5);
        testqueue.add(10);
        testqueue.add(-1);
        testqueue.add(2);
        testqueue.add(5);
        while (!testqueue.isEmpty()) {
            assertEquals(testqueue.remove(), heap.removeMin());
        }
        assertTrue(testqueue.isEmpty());
        assertTrue(testqueue.isEmpty());
    }

    
    @Test(timeout=SECOND)
    public void testInsertAndRemoveMinForManyNumbers() {
        IPriorityQueue<Integer> heap = this.makeInstance();
        int cap = 500;
        for (int i = cap - 1; i >= 0; i--) {
            heap.insert(i * 2);
        }
        assertEquals(cap, heap.size());
        for (int i = 0; i < cap; i++) {
            int value = heap.removeMin();
            assertEquals(i* 2, value);
        }
        assertTrue(heap.isEmpty());
    }
    
    public void testAlternatingInsertAndRemoveMin() {
        IPriorityQueue<Integer> heap = this.makeInstance();
        int cap = 500;
        for (int i = cap - 1; i >= 0; i--) {
            heap.insert(i * 2);
            assertEquals(1, heap.size());
            int value = heap.removeMin();
            assertEquals(i* 2, value);
            assertTrue(heap.isEmpty());
        }
    }
    
    @Test(timeout=SECOND)
    public void testInsertAndPeekMinForManyNumbers() {
        IPriorityQueue<Integer> heap = this.makeInstance();
        int cap = 500;
        for (int i = 0; i < cap; i++) {
            heap.insert((cap - i) * 2);
            int value = heap.peekMin();
            assertEquals((cap - i) * 2, value);
        }
        assertEquals(cap, heap.size());
    }
    
    @Test(timeout=SECOND)
    public void testRemoveMinToEmptyHeapThrowsException() {
        IPriorityQueue<Integer> heap = this.makeInstance();
        try {
            heap.removeMin();
            fail("Expected EmptyContainerException");
        } catch (EmptyContainerException ex) {
            // Do nothing: this is ok
        }
    }
    
    @Test(timeout=SECOND)
    public void testPeekMinToEmptyHeapThrowsException() {
        IPriorityQueue<Integer> heap = this.makeInstance();
        try {
            heap.peekMin();
            fail("Expected EmptyContainerException");
        } catch (EmptyContainerException ex) {
            // Do nothing: this is ok
        }
    }
    
    @Test(timeout=SECOND)
    public void testInsertNullThrowsException() {
        IPriorityQueue<Integer> heap = this.makeInstance();
        try {
            heap.insert(null);
            fail("IllegalArgumentException");
        } catch (IllegalArgumentException ex) {
            // Do nothing: this is ok
        }
    }
    
    @Test(timeout=SECOND)
    public void testLargeElement() {
        IPriorityQueue<String> heap = this.makeInstance();
        PriorityQueue<String> testqueue = new PriorityQueue<String>(5);
        heap.insert("abcdefghijklmnopqrstuvwxyzm");
        heap.insert("abcdefghijklmnopqrstuvwxyza");
        heap.insert("abcdefghijklmnopqrstuvwxyzc");
        heap.insert("abcdefghijklmnopqrstuvwxyzf");
        testqueue.add("abcdefghijklmnopqrstuvwxyzm");
        testqueue.add("abcdefghijklmnopqrstuvwxyza");
        testqueue.add("abcdefghijklmnopqrstuvwxyzc");
        testqueue.add("abcdefghijklmnopqrstuvwxyzf");
        while (!testqueue.isEmpty()) {
            assertEquals(testqueue.remove(), heap.removeMin());
        }
        assertTrue(testqueue.isEmpty());
        assertTrue(testqueue.isEmpty());
    }
}
