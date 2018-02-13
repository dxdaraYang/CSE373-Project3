package misc;

import java.util.Iterator;

import datastructures.concrete.ArrayHeap;
import datastructures.concrete.DoubleLinkedList;
import datastructures.interfaces.IList;

public class Searcher {
    /**
     * This method takes the input list and returns the top k elements
     * in sorted order.
     *
     * So, the first element in the output list should be the "smallest"
     * element; the last element should be the "biggest".
     *
     * If the input list contains fewer then 'k' elements, return
     * a list containing all input.length elements in sorted order.
     *
     * This method must not modify the input list.
     *
     * @throws IllegalArgumentException  if k < 0
     */
    public static <T extends Comparable<T>> IList<T> topKSort(int k, IList<T> input) {
        // Implementation notes:
        //
        // - This static method is a _generic method_. A generic method is similar to
        //   the generic methods we covered in class, except that the generic parameter
        //   is used only within this method.
        //
        //   You can implement a generic method in basically the same way you implement
        //   generic classes: just use the 'T' generic type as if it were a regular type.
        //
        // - You should implement this method by using your ArrayHeap for the sake of
        //   efficiency.

        if (k < 0) {
            throw new IllegalArgumentException();
        }
        ArrayHeap<T> heap = new ArrayHeap<>();
        DoubleLinkedList<T> output = new DoubleLinkedList<>();
        Iterator<T> iter = input.iterator();
        while (iter.hasNext()) {
            heap.insert(iter.next());
        }
        if (k < input.size()) {
            for (int i = 0; i < input.size() - k; i++) {
                T temp = heap.removeMin();
            }
            while (heap.size() != 0) {
                output.add(heap.removeMin());
            }
        } else {
            while (heap.size() != 0) {
                output.add(heap.removeMin());
            }
        }
        return output;
    }
}
