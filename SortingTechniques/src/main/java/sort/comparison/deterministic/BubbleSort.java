package sort.comparison.deterministic;

import sort.Sort;
import sort.SortUtil;

/**
 * BubbleSort is a simple, comparison-based sorting algorithm that repeatedly
 * steps through the list,
 * compares adjacent elements, and swaps them if they are in the wrong order.
 * The pass through the
 * list is repeated until no swaps are needed, which indicates that the list is
 * sorted.
 * <p>
 * * Time Complexity:
 * - Best Case: O(n) when the array is already sorted.
 * - Average Case: O(n²) due to the nested loop comparisons and swaps.
 * - Worst Case: O(n²) when the array is sorted in reverse order.
 * <p>
 * Space Complexity:
 * - O(1) since sorting is performed in-place without additional memory usage.
 * <p>
 * This implementation supports generic types by extending Comparable<T>.
 *
 * @param <T> The type of elements to be sorted, must implement Comparable<T>.
 */

public class BubbleSort<T extends Comparable<T>> extends Sort<T> {

    public BubbleSort(boolean showSteps) {
        super(showSteps);
    }

    /**
     * Sorts the given array using Bubble Sort.
     *
     * @param arr The array to be sorted.
     */
    @Override
    public void sort(T[] arr) {
        int size = arr.length;
        for (int i = 0; i < size - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < size - i - 1; j++) {
                if (arr[j].compareTo(arr[j + 1]) > 0) {
                    addStep(arr);
                    SortUtil.swap(arr, j, j + 1);
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
        addStep(arr);
    }
}