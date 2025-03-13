package sort.comparison.deterministic;

import sort.Sort;
import sort.SortUtil;

/**
 * Implementation of the Selection Sort algorithm.
 * Selection Sort repeatedly selects the smallest element from the unsorted portion
 * and swaps it with the first unsorted element.
 *
 * @param <T> The type of elements to be sorted, which must extend Comparable.
 */
public class SelectionSort<T extends Comparable<T>> extends Sort<T> {

    public SelectionSort(boolean showSteps) {
        super(showSteps);
    }

    /**
     * Sorts the given array using Selection Sort.
     *
     * @param arr The array to be sorted.
     */
    @Override
    public void sort(T[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            addStep(arr);
            int index = SortUtil.indexOfMin(arr, i);
            SortUtil.swap(arr, i, index);
        }
        addStep(arr);
    }
}