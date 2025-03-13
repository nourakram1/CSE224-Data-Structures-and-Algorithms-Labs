package sort.comparison.deterministic;

import sort.Sort;
import sort.SortUtil;
import java.util.Comparator;

/**
 * BubbleSort is a simple, comparison-based sorting algorithm that repeatedly
 * steps through the list, compares adjacent elements, and swaps them if they are in the wrong order.
 * The pass through the list is repeated until no swaps are needed, indicating that the list is sorted.
 *
 * <p><b>Time Complexity:</b></p>
 * - Best Case: O(n) when the array is already sorted.
 * - Average Case: O(n²) due to the nested loop comparisons and swaps.
 * - Worst Case: O(n²) when the array is sorted in reverse order.
 *
 * <p><b>Space Complexity:</b></p>
 * - O(1) since sorting is performed in-place without additional memory usage.
 *
 * <p>This implementation supports generic types by extending Comparable<T>.</p>
 *
 * @param <T> The type of elements to be sorted, must implement Comparable<T>.
 */
public class BubbleSort<T extends Comparable<T>> extends Sort<T> {

    public BubbleSort(boolean showSteps) {
        super(showSteps);
    }

    /**
     * Sorts the given array using Bubble Sort with natural ordering.
     *
     * @param arr The array to be sorted.
     */
    @Override
    public void sort(T[] arr) {
        sort(arr, Comparator.naturalOrder());
    }

    /**
     * Sorts the given array using Bubble Sort with a custom comparator.
     *
     * @param arr The array to be sorted.
     * @param comparator The comparator defining the sorting order.
     */
    @Override
    public void sort(T[] arr, Comparator<T> comparator) {
        int size = arr.length;
        for (int i = 0; i < size - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < size - i - 1; j++) {
                if (comparator.compare(arr[j], arr[j + 1]) > 0) {
                    SortUtil.swap(arr, j, j + 1);
                    swapped = true;
                    addStep(arr);
                }
            }
            if (!swapped) {
                break;
            }
        }
        addStep(arr);
    }
}