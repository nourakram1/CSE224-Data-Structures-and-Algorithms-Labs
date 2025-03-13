package sort.comparison.deterministic;

import sort.Sort;
import sort.SortUtil;

import java.util.Comparator;

/**
 * Implements the in-place Heap Sort algorithm using a customizable comparator.
 * Heap Sort builds a heap from the input array and then repeatedly extracts the largest (or smallest) element,
 * ensuring a sorted order.
 *
 * @param <T> The type of elements to be sorted, which must extend Comparable.
 */
public class HeapSort<T extends Comparable<T>> extends Sort<T> {

    public HeapSort(boolean showSteps) {
        super(showSteps);
    }

    /**
     * Sorts the given array using Heap Sort with natural ordering.
     *
     * @param arr The array to be sorted.
     */
    @Override
    public void sort(T[] arr) {
        sort(arr, Comparator.naturalOrder());
    }

    /**
     * Sorts the given array using Heap Sort with a custom comparator.
     *
     * @param arr The array to be sorted.
     * @param comparator The comparator defining the sorting order.
     */
    @Override
    public void sort(T[] arr, Comparator<T> comparator) {
        int n = arr.length;

        // Build the heap
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i, comparator);
        }

        // Extract elements from the heap one by one
        for (int i = n - 1; i > 0; i--) {
            SortUtil.swap(arr, 0, i);  // Move the root (max or min) to the end
            heapify(arr, i, 0, comparator);  // Restore heap property
            addStep(arr);
        }
    }

    /**
     * Maintains the heap property by ensuring the subtree rooted at index `i` is a heap.
     *
     * @param arr        The array representing the heap.
     * @param n          The size of the heap.
     * @param i          The index of the root of the subtree.
     * @param comparator The comparator defining the sorting order.
     */
    private void heapify(T[] arr, int n, int i, Comparator<T> comparator) {
        int largest = i;  // Assume root as largest
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && comparator.compare(arr[left], arr[largest]) > 0) {
            largest = left;
        }

        if (right < n && comparator.compare(arr[right], arr[largest]) > 0) {
            largest = right;
        }

        if (largest != i) {
            SortUtil.swap(arr, i, largest);
            heapify(arr, n, largest, comparator);
        }
    }

}
