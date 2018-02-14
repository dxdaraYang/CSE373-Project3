package datastructures.sorting;

import misc.BaseTest;
import misc.Searcher;

import org.junit.Test;

import datastructures.concrete.ArrayHeap;
import datastructures.concrete.DoubleLinkedList;
import datastructures.interfaces.IList;
import datastructures.interfaces.IPriorityQueue;

import static org.junit.Assert.assertTrue;

/**
 * See spec for details on what kinds of tests this class should include.
 */
public class TestSortingStress extends BaseTest {
    protected <T extends Comparable<T>> IPriorityQueue<T> makeInstance() {
        return new ArrayHeap<>();
    }
    
    @Test(timeout=10*SECOND)
    public void testArrayHeapInsertEfficient() {
        IPriorityQueue<Integer> heap = this.makeInstance();
        int cap = 200000;
        for (int i = cap - 1; i >= 0; i--) {
            heap.insert(i * 2);
        }
        assertEquals(cap, heap.size());
        for (int i = 0; i < cap; i++) {
            int value = heap.removeMin();
            System.out.println(i*2 + "," + value);
            assertEquals(i* 2, value);
        }
        assertTrue(heap.isEmpty());
        
    }
    
    @Test(timeout=10*SECOND)
    public void testAlternatingInsertAndRemoveMinEfficient() {
        IPriorityQueue<Integer> heap = this.makeInstance();
        int cap = 1000000;
        for (int i = cap - 1; i >= 0; i--) {
            heap.insert(i * 2);
            assertEquals(1, heap.size());
            int value = heap.removeMin();
            assertEquals(i* 2, value);
            assertTrue(heap.isEmpty());
        }
    }
    
    @Test(timeout=10*SECOND)
    public void testInsertAndRemoveMinEfficient() {
        IPriorityQueue<Integer> heap = this.makeInstance();
        int cap = 1000000;
        for (int i = cap - 1; i >= 0; i--) {
            heap.insert(i * 2);
            assertEquals(cap - i, heap.size());
        }
        for (int i = 0; i < cap; i++) {
            int value = heap.removeMin();
            assertEquals(i* 2, value);
        }
        assertTrue(heap.isEmpty());
    }
    
    @Test(timeout=10*SECOND)
    public void testInsertAndPeekMinEfficient() {
        IPriorityQueue<Integer> heap = this.makeInstance();
        int cap = 1000000;
        for (int i = cap - 1; i >= 0; i--) {
            heap.insert(i * 2);
            assertEquals(cap - i, heap.size());
            int value = heap.peekMin();
            assertEquals(i * 2, value);
            assertEquals(cap - i, heap.size());
        }
    }
    
    @Test(timeout=10*SECOND)
    public void testSortEfficient() {
        IList<Integer> list = new DoubleLinkedList<>();
        int cap = 100000;
        for (int i = 0; i < cap; i++) {
            list.add(i);
        }

        IList<Integer> top = Searcher.topKSort(cap, list);
        assertEquals(cap, top.size());
        for (int i = 0; i < top.size(); i++) {
            assertEquals(i, top.get(i));
        }
    }
    
    @Test(timeout=10*SECOND)
    public void testSortFewOfLargeArray() {
        IList<Integer> list = new DoubleLinkedList<>();
        int cap = 200000;
        for (int i = cap; i > 0; i--) {
            list.add(i);
        }
        IList<Integer> top = Searcher.topKSort(5, list);
        assertEquals(5, top.size());
        for (int i = 0; i < top.size(); i++) {
            assertEquals(199996+i, top.get(i));
        }
    }
}