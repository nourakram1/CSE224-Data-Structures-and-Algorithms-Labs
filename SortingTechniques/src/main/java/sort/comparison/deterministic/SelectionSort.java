package sort.comparison.deterministic;

import sort.Sort;
import sort.SortUtil;

import java.util.Comparator;

/**
 * Implements the Selection Sort algorithm, which repeatedly selects the smallest (or largest) element
 * from the unsorted portion of the array and swaps it with the first unsorted element.
 *
 * @param <T> The type of elements to be sorted, which must implement Comparable<T>.
 */
public class SelectionSort<T extends Comparable<T>> extends Sort<T> {

    public SelectionSort(boolean showSteps) {
        super(showSteps);
    }

    /**
     * Sorts the given array using Selection Sort with natural ordering.
     *
     * @param arr The array to be sorted.
     */
    @Override
    public void sort(T[] arr) {
        sort(arr,  Comparator.naturalOrder());
    }

    /**
     * Sorts the given array using Selection Sort with a custom comparator.
     *
     * @param arr        The array to be sorted.
     * @param comparator Comparator defining the sorting order.
     */
    @Override
    public void sort(T[] arr, Comparator<T> comparator) {
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {
            addStep(arr);
            int index = i;

            // Find the minimum (or maximum) element in the unsorted portion of the array
            for (int j = i + 1; j < n; j++) {
                if (comparator.compare(arr[j], arr[index]) < 0) {
                    index = j;
                }
            }

            // Swap the found minimum element with the first element
            if (i != index) {
                SortUtil.swap(arr, i, index);
            }
        }
        addStep(arr);
    }
}
