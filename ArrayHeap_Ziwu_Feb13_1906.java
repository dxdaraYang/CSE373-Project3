package datastructures.concrete;

import datastructures.interfaces.IPriorityQueue;
import misc.exceptions.EmptyContainerException;

/**
 * See IPriorityQueue for details on what each method must do.
 */
public class ArrayHeap<T extends Comparable<T>> implements IPriorityQueue<T> {
    // See spec: you must implement a implement a 4-heap.
    private static final int NUM_CHILDREN = 4;

    // You MUST use this field to store the contents of your heap.
    // You may NOT rename this field: we will be inspecting it within
    // our private tests.
    private T[] heap;

    // Feel free to add more fields and constants.
    private int size;

    public ArrayHeap() {
        this.heap = makeArrayOfT(NUM_CHILDREN);
        this.size = 0;
    }

    /**
     * This method will return a new, empty array of the given size
     * that can contain elements of type T.
     *
     * Note that each element in the array will initially be null.
     */
    @SuppressWarnings("unchecked")
    private T[] makeArrayOfT(int size) {
        // This helper method is basically the same one we gave you
        // in ArrayDictionary and ChainedHashDictionary.
        //
        // As before, you do not need to understand how this method
        // works, and should not modify it in any way.
        return (T[]) (new Comparable[size]);
    }

    @Override
    public T removeMin() {
        if (size == 0) {
            throw new EmptyContainerException();
        } 
        T root = heap[0];
        if (size == 1) {
            heap[0] = null;
            size--;
            return root;
        }
        heap[0] = heap[size-1];
        heap[size-1] = null;
        percolateDown(0);
        size--;
        return root;
    }

    @Override
    public T peekMin() {
        if (size == 0) {
            throw new EmptyContainerException();
        }
        return heap[0];
    }

    @Override
    public void insert(T item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (size >= heap.length) {
            int length = heap.length;
            T[] newArray = makeArrayOfT(length*2);
            for (int i = 0; i < length; i++) {
                newArray[i] = heap[i];
            }
            heap = newArray;
        }
        heap[size] = item;
        percolateUp(size);
        size++;
    }

    @Override
    public int size() {
        return this.size;
    }
    
    private int childrenIndex(int parentIndex, int n_thChild) {
        return parentIndex * NUM_CHILDREN + n_thChild;
    }
    
    private int parentIndex(int childIndex) {
        return (childIndex - 1) / NUM_CHILDREN;
    }
    
    private int minChildIndex(int parentIndex) {
        int minIndex = 1;
        for (int i = 2; i <= NUM_CHILDREN; i++) {
            if (heap[childrenIndex(parentIndex, i)].
                    compareTo(heap[childrenIndex(parentIndex, minIndex)]) < 0) {
                minIndex = i;
            }
        }
        return minIndex;
    }
    
    private boolean lessThanParent(int index) {
        return (index != 0 && heap[index].compareTo(heap[parentIndex(index)]) < 0);
    }
    
    private boolean hasAChild(int parentIndex) {
        return heap[childrenIndex(parentIndex, 1)] != null;
    }
    
    private void swap(int firstIndex, int secondIndex) {
        T temp = heap[firstIndex];
        heap[firstIndex] = heap[secondIndex];
        heap[secondIndex] = temp;
    }
    
    private void percolateUp(int index) {
        int localIndex = index;
        while (lessThanParent(localIndex)) {
            int localParentIndex = parentIndex(localIndex);
            swap(localIndex, localParentIndex);
            localIndex = localParentIndex;
        }
    }
    
    private void percolateDown(int index) {
        int localIndex = index;
        while (hasAChild(localIndex) && lessThanParent(minChildIndex(localIndex))) {
            int localChildIndex = minChildIndex(localIndex);
            swap(localChildIndex, localIndex);
            localIndex = localChildIndex;
        }
    }
}
