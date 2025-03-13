package sort.comparison.deterministic;

import sort.Sort;

import java.util.ArrayList;
import java.util.List;

/**
 * Implements the Merge Sort algorithm, a divide-and-conquer sorting method
 * that recursively splits an array into smaller subarrays, sorts them, and merges them back.
 *
 * @param <T> The type of elements to be sorted, which must extend Comparable.
 */
public class MergeSort<T extends Comparable<T>> extends Sort<T> {

    public MergeSort(boolean showSteps) {
        super(showSteps);
    }

    /**
     * Merges two sorted subarrays into a single sorted subarray.
     *
     * @param arr The original array containing the subarrays.
     * @param l   The starting index of the left subarray.
     * @param m   The middle index separating the subarrays.
     * @param r   The ending index of the right subarray.
     */
    private void merge(T[] arr, int l, int m, int r) {
        addStep(arr);

        int i = l;
        int j = m + 1;
        List<T> list = new ArrayList<>();

        while (i <= m && j <= r) {
            if (arr[i].compareTo(arr[j]) <= 0) {
                list.add(arr[i++]);
            } else {
                list.add(arr[j++]);
            }
        }
        while (i <= m) {
            list.add(arr[i++]);
        }
        while (j <= r) {
            list.add(arr[j++]);
        }

        for (int k = l; k <= r; k++) {
            arr[k] = list.get(k - l);
        }
    }

    /**
     * Recursively divides the array into smaller subarrays until each subarray contains one element,
     * then merges them back in sorted order.
     *
     * @param arr The array to be sorted.
     * @param l   The leftmost index of the current subarray.
     * @param r   The rightmost index of the current subarray.
     */
    private void divide(T[] arr, int l, int r) {
        if (l < r) {
            int m = (l + r) / 2;
            divide(arr, l, m);
            divide(arr, m + 1, r);
            merge(arr, l, m, r);
        }
    }

    /**
     * Sorts the given array using Merge Sort.
     *
     * @param arr The array to be sorted.
     */
    @Override
    public void sort(T[] arr) {
        divide(arr, 0, arr.length - 1);
        addStep(arr);
    }
}