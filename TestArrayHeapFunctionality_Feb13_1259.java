package datastructures.sorting;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import misc.BaseTest;
import misc.exceptions.EmptyContainerException;
import datastructures.concrete.ArrayHeap;
import datastructures.interfaces.IList;
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
    public void testInsertBasic() {
        IPriorityQueue<Integer> heap = this.makeInstance();
        int[] test = new int[4];
        
        heap.insert(10);
        test[0] = 10;
        this.assertEquals(test, heap);
        
        heap.insert(-1);
        test[1] = -1;
        this.assertEquals(test, heap);

        heap.insert(2);
        test[2] = 2;
        this.assertEquals(test, heap);

        heap.insert(5);
        test[3] = 5;
        this.assertEquals(test, heap);
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
}
