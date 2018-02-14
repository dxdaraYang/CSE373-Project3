package datastructures.sorting;

import misc.BaseTest;
import datastructures.concrete.DoubleLinkedList;
import datastructures.interfaces.IList;
import datastructures.interfaces.IPriorityQueue;
import misc.Searcher;

import static org.junit.Assert.fail;

import org.junit.Test;

/**
 * See spec for details on what kinds of tests this class should include.
 */
public class TestTopKSortFunctionality extends BaseTest {
    @Test(timeout=SECOND)
    public void testSimpleUsage() {
        IList<Integer> list = new DoubleLinkedList<>();
        for (int i = 0; i < 20; i++) {
            list.add(i);
        }

        IList<Integer> top = Searcher.topKSort(5, list);
        assertEquals(5, top.size());
        for (int i = 0; i < top.size(); i++) {
            assertEquals(15+i, top.get(i));
        }
    }
    
    @Test(timeout=SECOND)
    public void testSortMany() {
        IList<Integer> list = new DoubleLinkedList<>();
        for (int i = 200; i > 0; i++) {
            list.add(i);
        }

        IList<Integer> top = Searcher.topKSort(100, list);
        assertEquals(100, top.size());
        for (int i = 0; i < top.size(); i++) {
            assertEquals(100 + i, top.get(i));
        }
    }
    
    public void testSortBasic() {// need edit
        IList<Integer> list = new DoubleLinkedList<>();
        list.add(10);
        list.add(1);
        list.add(5);
        list.add(-2);
        list.add(0);
        
        IList<Integer> top = Searcher.topKSort(3, list);        
        assertEquals(3, top.size());
        assertEquals(1,top.get(0));
        assertEquals(5,top.get(1));
        assertEquals(10,top.get(2));
        
        
        IList<Integer> top2 = Searcher.topKSort(5, list);        
        assertEquals(5, top2.size());
        assertEquals(-2,top2.get(0));
        assertEquals(0,top2.get(1));
        assertEquals(1,top2.get(2));
        assertEquals(5,top2.get(3));
        assertEquals(10,top2.get(4));
        
    }
    
    @Test(timeout=SECOND)
    public void testSortEmpty() {
        IList<Integer> list = new DoubleLinkedList<>();       
        IList<Integer> top = Searcher.topKSort(10, list); // should return a empty list
        assertEquals(0,top.size());
        assertEquals(list,top);
    }
    
    @Test(timeout=SECOND)
    public void negativeKThrowsException() {
        IList<Integer> list = new DoubleLinkedList<>();
        for (int i = 0; i < 20; i++) {
            list.add(i);
        }        
        try {
            Searcher.topKSort(-1, list);
            fail("IllegalArgumentException");
        } catch (IllegalArgumentException ex) {
            // Do nothing: this is ok
        }
    }
    
    @Test(timeout=SECOND)
    public void testSortZeroElements() {
        IList<Integer> list = new DoubleLinkedList<>();
        for (int i = 0; i < 20; i++) {
            list.add(i);
        }
        IList<Integer> top = Searcher.topKSort(0, list);
        assertEquals(0, top.size());
        
    }
    
    
    @Test(timeout=5*SECOND)
    public void testSortFewOfLargeArray() {
        IList<Integer> list = new DoubleLinkedList<>();
        int cap = 100000;
        for (int i = 0; i < cap; i++) {
            list.add(i);
        }
        IList<Integer> top = Searcher.topKSort(5, list);
        assertEquals(5, top.size());
        
    }
    
    
}
