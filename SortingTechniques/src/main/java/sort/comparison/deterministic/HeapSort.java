package sort.comparison.deterministic;

import sort.Sort;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Implements the Heap Sort algorithm using a priority queue (min-heap).
 * Heap Sort extracts the smallest element from the heap and places it in the sorted order.
 *
 * @param <T> The type of elements to be sorted, which must extend Comparable.
 */
public class HeapSort <T extends Comparable<T>> extends Sort<T> {

    public HeapSort(boolean showSteps) {
        super(showSteps);
    }

    /**
     * Sorts the given array using Heap Sort.
     *
     * @param arr The array to be sorted.
     */
    @Override
    public void sort(T[] arr) {
        PriorityQueue<T> pq = new PriorityQueue<>();

        pq.addAll(Arrays.asList(arr));

        for (int i = 0; i < arr.length; i++) {
            arr[i] = pq.poll();
        }
    }
}