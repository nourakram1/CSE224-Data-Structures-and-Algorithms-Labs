package sort.comparison.deterministic;

import sort.Sort;
import java.util.Comparator;

/**
 * InsertionSort is a simple, comparison-based sorting algorithm that builds the final sorted array
 * one element at a time by repeatedly picking the next element and inserting it into its correct
 * position within the sorted portion of the array.
 * <p>
 * The algorithm is efficient for small datasets and partially sorted arrays but becomes slow on
 * large datasets due to its quadratic time complexity.
 * <p>
 * Time Complexity:
 * - Best Case: O(n) when the array is already sorted.
 * - Average Case: O(n²) due to the nested loop comparisons and shifts.
 * - Worst Case: O(n²) when the array is sorted in reverse order.
 * <p>
 * Space Complexity:
 * - O(1) since sorting is performed in-place without additional memory usage.
 * <p>
 * This implementation supports generic types by extending Comparable<? super T>.
 *
 * @param <T> The type of elements to be sorted, must implement Comparable<? super T>.
 */
public class InsertionSort<T extends Comparable<? super T>> extends Sort<T> {

    public InsertionSort(boolean showSteps) {
        super(showSteps);
    }

    /**
     * Sorts the input array using the Insertion Sort algorithm with natural ordering.
     *
     * @param arr Array to sort.
     */
    @Override
    public void sort(T[] arr) {
        sort(arr, Comparator.naturalOrder());
    }

    /**
     * Sorts the input array using the Insertion Sort algorithm with a custom comparator.
     *
     * @param arr        Array to sort.
     * @param comparator Comparator defining the sorting order.
     */
    @Override
    public void sort(T[] arr, Comparator<? super T> comparator) {
        for (int i = 1; i < arr.length; i++) {
            addStep(arr);

            T key = arr[i];  // Store the current element to be compared
            int j = i - 1;

            // Shift elements that are greater than key to one position ahead of their current position
            while (j >= 0 && comparator.compare(key, arr[j]) < 0) {
                arr[j + 1] = arr[j];
                j--;
            }

            // Insert the key into its correct position
            arr[j + 1] = key;
        }
        addStep(arr);
    }
}
